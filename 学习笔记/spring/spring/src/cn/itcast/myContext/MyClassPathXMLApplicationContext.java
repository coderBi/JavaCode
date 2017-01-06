package cn.itcast.myContext;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import com.sun.org.apache.commons.beanutils.ConvertUtils;

/**
 * @处理范围说明： 我的这种自己写的classpathxmlapplicationcontext，只能处理有id 有class属性的bean，
 *          对于其他的bean，会自动忽略处理。
 * @处理方式说明： 处理的方式是将bean.xml文件进行sax解析，然后把每一个bean节点添加到一个自定义个MyBean的list里面
 *          ，之后通过这个list里面每一个对象的id跟className可以实例化一个对象添加到一个map集合里面，key是id，value是
 *          这个通过className实例化的对象
 *          。然后通过遍历MyBean的list里面每一各对象的properties，将这个bean对应的map里面的
 *          对象的property里面的name对应的属性，利用反射填充为map里面key为property的ref的对象。
 * 
 * @author Edwin
 * @since 2016年7月16日11:17:40
 * 
 */
public class MyClassPathXMLApplicationContext {
	private List<MyBean> myBeans;
	private Map<String, Object> singletons;

	public MyClassPathXMLApplicationContext(String fileName) {
		myBeans = new ArrayList<MyBean>();
		singletons = new HashMap<String, Object>();
		readConfigXml(fileName);
		initInstances();
		annotationInject(); // 注解
		inject();
	}

	public MyClassPathXMLApplicationContext() {
		this("beans.xml"); // 默认路径是beans.xml
	}

	/**
	 * 这是我写的最新的注解分析代码
	 */
	private void annotationInject() {
		for (Object bean : singletons.values()) {
			if (null == bean)
				continue;
			Field[] fields = bean.getClass()// getFields();这个方法得到的是所有的public的属性
					.getDeclaredFields();
			// 对所有的属性进行分析
			for (Field field : fields) {
				if (null != field.getAnnotation(myAnnotation.class)) {

					String name = field.getAnnotation(myAnnotation.class)
							.name();
					Object beanToInject = getBeanToInject(name,
							field.getName(), field.getType());
					field.setAccessible(true); // 设置可以访问
					try {
						field.set(bean, beanToInject);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			// 对所有的方法进行注解分析
			try {
				PropertyDescriptor[] propertyDescriptors = Introspector
						.getBeanInfo(bean.getClass()).getPropertyDescriptors(); // 得到所有的属性跟他们的读方法跟写方法，对应的方法可能为空。其中有一个属性的名称是class
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					Method method = propertyDescriptor.getWriteMethod();
					if (null != method // 一个属性可能没有对应的写方法
							&& null != method.getAnnotation(myAnnotation.class)) {
						String name = method.getAnnotation(myAnnotation.class)
								.name();
						Object beanToInject = getBeanToInject(name,
								propertyDescriptor.getName(),
								propertyDescriptor.getPropertyType());
						method.setAccessible(true); // 设置可以访问
						try {
							method.invoke(bean, beanToInject);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}

			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 这是我之前写的一个版本的注解处理函数，其中存在两个问题，所以又进行了优化重写，目前这个函数放在这里仅作对比分析
	 * 
	 * @之前存在的两个问题是： 1，对于属性的注解，这里处理的方式是如果属性没有set方法就不进行注入，但是spring处理的是会通过反射强制注入。
	 *              2，对于方法的注入，这里是通过分析这个方法是不是一个属性的写方法，如果是就进行相应的注入，
	 *              但是如果通过Introspector直接获取到的属性跟他对应的读方法跟写方法。
	 */
	private void annotationInject1() {
		for (Object bean : singletons.values()) {
			if (null == bean)
				continue;
			Field[] fields = bean.getClass().getDeclaredFields();
			Method[] methods = bean.getClass().getDeclaredMethods();
			PropertyDescriptor[] propertyDescriptors = null;
			try {
				propertyDescriptors = Introspector.getBeanInfo(bean.getClass())
						.getPropertyDescriptors();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			for (Field field : fields) {
				String annotationName = getAnnotationAddedName(field, null);
				if (null != annotationName) {
					// 如果有注解
					Object beanToInject = getBeanToInject(annotationName,
							field.getName(), field.getType());
					for (PropertyDescriptor pd : propertyDescriptors) {
						if (pd.getName().equals(field.getName())) {
							Method methodToInvoke = pd.getWriteMethod();
							try {
								methodToInvoke.invoke(bean, beanToInject);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			for (Method method : methods) {
				Boolean isSetter = false;
				PropertyDescriptor pdSave = null; // 如果这个method是属性的写方法，就用于保存method对应的属性介绍
				for (PropertyDescriptor pd : propertyDescriptors) {
					if (null != pd.getWriteMethod()
							&& pd.getWriteMethod().getName()
									.equals(method.getName())) {
						// 如果这个方法是属性的写方法，设置isSetter = true，进行注解
						isSetter = true;
						pdSave = pd;
						break;
					}
				}
				if (isSetter == false)
					continue;
				String annotationName = getAnnotationAddedName(null, method);
				if (null != annotationName) {
					// 如果有注解
					Object beanToInject = getBeanToInject(annotationName,
							pdSave.getName(), pdSave.getPropertyType());
					try {
						method.invoke(bean, beanToInject);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private String getAnnotationAddedName(Field field, Method setter) {
		if (null != field && field.isAnnotationPresent(myAnnotation.class)) {
			return field.getAnnotation(myAnnotation.class).name();
		}
		if (null != setter && setter.isAnnotationPresent(myAnnotation.class)) {
			return setter.getAnnotation(myAnnotation.class).name();
		}
		return null;
	}

	/**
	 * 获取跟注解的name或者字段的名称或者跟属性类型相匹配的bean
	 * 
	 * @param name
	 *            注解的name属性
	 * @param fieldName
	 *            属性的名字
	 * @param type
	 *            属性的类型
	 * @return 如果找到返回相应的bean对象，否则返回null
	 */
	private Object getBeanToInject(String name, String fieldName, Class<?> type) {
		for (String key : singletons.keySet()) {
			Object bean = singletons.get(key);
			if (null != name && name.equals(key)// 注解的name属性跟bean的id相匹配
					|| null != fieldName && fieldName.equals(key) // 找到id跟属性名相同的bean
					|| null != type && type.isAssignableFrom(bean.getClass())// 属性类型是这个bean类型的实现接口或者父类或者本身
			)
				return bean;
		}
		return null;
	}

	private void inject() {
		if (null == myBeans)
			return;
		for (MyBean myBean : myBeans) {
			if (null == myBean.getProperties())
				continue;
			Object singleton = singletons.get(myBean.getId());
			PropertyDescriptor[] properties;
			try {
				properties = Introspector.getBeanInfo(singleton.getClass())
						.getPropertyDescriptors();
				for (MyProperty myProperty : myBean.getProperties()) {
					for (PropertyDescriptor pd : properties) {
						if (!pd.getName().equals(myProperty.getName()))
							continue; // 如果属性名不是当前property里面存放的name就接着寻找
						Method method = pd.getWriteMethod();
						method.setAccessible(true); // 设置具有访问权限
						Object objToInject = null;
						if (myProperty.getValue() != null) {
							// 如果存在value，就是要赋基本值到属性。这时可能存在String类型到其他类型的转换。经过测试，发现如果本身这个值就需要是String，直接传入转换函数会抛异常
							objToInject = myProperty.getValue() instanceof java.lang.String ? myProperty
									.getValue() : ConvertUtils
									.convert(myProperty.getValue(),
											pd.getPropertyType());
						} else if (myProperty.getRef() != null) {
							objToInject = singletons.get(myProperty.getRef());
						} else if (null != myProperty.getSubBean()) {
							objToInject = createOjbectFromClassName(myProperty
									.getSubBean().getClassName());
						} else {
							// 如果在map里面没有找到属性ref对应的对象，就查找这个bean的内部bean，如果没有内部bean就抛异常
							throw new RuntimeException("id为" + myBean.getId()
									+ "的bean里面的property属性name为"
									+ myProperty.getName()
									+ "的项存在没有可用的ref或者内部bean的异常");
						}
						method.invoke(singleton, objToInject);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过myBeans初始化singletons
	 */
	private void initInstances() {
		if (null == myBeans) {
			return;
		}
		for (MyBean myBean : myBeans) {
			// 这里的id，className都是符合要求的，因为在创建myBeans的时候进行了筛选
			String id = myBean.getId();
			String className = myBean.getClassName();
			Object obj = createOjbectFromClassName(className);
			singletons.put(id, obj);
		}
	}

	/**
	 * 读取xml配置文件，从中筛选出id、className都存在的bean节点，利用这些节点信息创建MyBean对象并且添加到myBeans里面
	 * 
	 * @param fileName
	 *            要读取的xml配置文件名
	 */
	private void readConfigXml(String fileName) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		URL url = this.getClass().getClassLoader().getResource(fileName);
		try {
			document = saxReader.read(url);
			Map<String, String> nsMap = new HashMap<String, String>();
			// 加入命名空间，sax解析，这里不是很懂
			nsMap.put("ns", "http://www.springframework.org/schema/beans");
			XPath xpath = document.createXPath("//ns:beans/ns:bean"); // 里面路径,开头需要两个反斜杠，原因未知
			xpath.setNamespaceURIs(nsMap);
			List<Element> nodes = xpath.selectNodes(document);
			// 遍历beans节点，创建Mybeanlist
			for (Element element : nodes) {
				String id = element.attributeValue("id");
				String className = element.attributeValue("class");

				if (isNullOrBlankString(id) || isNullOrBlankString(className)) {
					// 判断id或者name如果不符合要求，就放弃处理这个node
					continue;
				}
				id = id.trim();
				className = className.trim();
				MyBean mybean = new MyBean(id, className);
				xpath = element.createXPath("ns:property");
				xpath.setNamespaceURIs(nsMap);
				List<Element> properties = xpath.selectNodes(element);
				List<MyProperty> myPropertyList = new ArrayList<MyProperty>();
				for (Element property : properties) {
					String name = property.attributeValue("name"); // 获取属性不需要带命名空间，因为空间是作用在标签上的，用于区分不同的标签的
					String ref = property.attributeValue("ref");
					String value = property.attributeValue("value");
					MyBean subBean = null;
					xpath = property.createXPath("ns:bean");
					xpath.setNamespaceURIs(nsMap);
					List<Element> subBeans = xpath.selectNodes(property);
					if (!subBeans.isEmpty()) {
						Element myBeanElement = subBeans.get(0);
						String classNameInSubBean = myBeanElement
								.attributeValue("class");
						if (classNameInSubBean == null)
							throw new RuntimeException("id为" + id
									+ "的bean下面name为" + name
									+ "的property的内部bean的class未提供的异常");
						subBean = new MyBean(null, classNameInSubBean);
					}
					MyProperty myProperty = new MyProperty(name, ref, value,
							subBean);
					myPropertyList.add(myProperty);
				}
				mybean.setProperties(myPropertyList);
				myBeans.add(mybean);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个字符串是否是null或者是“”。其中判断是否是“”是trim之后的结果
	 * 
	 * @param str
	 * @return
	 */
	private Boolean isNullOrBlankString(String str) {
		if (null == str) {
			return true;
		}
		if ("" == str.trim()) {
			return true;
		}
		return false;
	}

	public Object getBean(String id) {
		return singletons.get(id);
	}

	private Object createOjbectFromClassName(String className) {
		Object obj = null;
		try {
			obj = this.getClass().getClassLoader().loadClass(className)
					.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}

package cn.com.code4fun.rpc.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;

/**
 * @className: MyBeanDefinationParser
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 14:41
 * @version: 0.0.1
 */
public class MyBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;

    public MyBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String beanName = element.getAttribute("id");
        // 注册
        parserContext.getRegistry().registerBeanDefinition(beanName, beanDefinition);
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            if (!isProperty(method, beanClass)) {
                continue;
            }
            String name = method.getName();
            String methodName = name.substring(3, 4).toLowerCase(Locale.ROOT) + name.substring(4);
            String value = element.getAttribute(methodName);
            beanDefinition.getPropertyValues().addPropertyValue(methodName, value);
        }
        return beanDefinition;
    }

    private boolean isProperty(Method method, Class beanClass) {
        String name = method.getName();
        boolean flag = name.length() > 3 && name.startsWith("set") && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1;
        Method getter = null;
        if (!flag) {
            return false;
        }

        Class<?> type = method.getParameterTypes()[0];
        try {
            getter = beanClass.getMethod("get" + name.substring(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == getter) {
            try {
                getter = beanClass.getMethod("is" + name.substring(3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());
        return flag;
    }
}

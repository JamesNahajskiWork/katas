package kata.annotation;

import kata.annotation.annotations.LoggerAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class LoggerAnnotationBeanProcessor implements BeanPostProcessor {

    Logger logger = LoggerFactory.getLogger(LoggerAnnotationBeanProcessor.class);

    private ConfigurableListableBeanFactory configurableBeanFactory;

    @Autowired
    public LoggerAnnotationBeanProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.configurableBeanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        this.runOnLoggerAnotation(bean);
        return bean;
    }


    private void runOnLoggerAnotation(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
//        logger.info(managedBeanClass.getSimpleName()); //+ Arrays.toString(managedBeanClass.getAnnotations()));
        if (!managedBeanClass.isAnnotationPresent(LoggerAnnotation.class)) {
            return;
        }
//        Method[] methods = managedBeanClass.getMethods();

        printVariablesInClass(bean, managedBeanClass, 0);

        logger.info("LOG 29 -----> " + managedBeanClass.getSimpleName());
    }

    private void printVariablesInClass(Object bean, Class<?> managedBeanClass, int indentation) {
        Field[] fields = managedBeanClass.getDeclaredFields();
        String nameIndentation = "\t".repeat(indentation * 2);
        String varIndentation = "\t".repeat((indentation * 2) + 2);
        logger.info(nameIndentation + "Object: " + managedBeanClass.getSimpleName());
        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                Class<?> targetType = field.getType();
//                if (!printFieldIfMissingToString(targetType, field, bean, indentation)) {
                    logger.info(
                            varIndentation + "{} : {}",
                            field.getName(), field.get(bean));
//                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                field.setAccessible(false);
            }
        });
    }

    private boolean printFieldIfMissingToString(Class<?> targetType, Field field, Object parentBean, int indentation) throws IllegalAccessException {
        boolean missingToString = (!targetType.isPrimitive() || Arrays.stream(targetType.getMethods()).noneMatch(method -> method.getName().equals("toString")));
        if (!missingToString) {
            return false;
        }
        Object childBean = field.get(parentBean);
        printVariablesInClass(childBean, childBean.getClass(), indentation + 1);
        return true;
    }
}
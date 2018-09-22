package org.terukusu.example.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtil {
    public static String dump(Object bean) {
        if (bean == null) {
            return "null";
        }

        // Getterの一覧取得(不要そうなのは除去)
        // TODO パラメタ化されたクラスの継承時にObject型のgetterが紛れ込むので要調査
        List<Method> getters = Arrays.stream(bean.getClass().getMethods()).filter(m -> m.getName().startsWith("get")
                && m.getParameterCount() == 0
                && !"getClass".equals(m.getName())
                && !("getId".equals(m.getName()) && m.getReturnType().equals(Object.class)))
                .collect(Collectors.toList());

        final StringBuilder sb = new StringBuilder();

        getters.forEach(getter -> {
            if (sb.length() > 0) {
                sb.append(",");
            }

            String propertyName = StringUtil.accessorToProperty(getter.getName());
            Object value = null;
            try {
                value = getter.invoke(bean);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Log.warn(e);
            }

            value = value != null ? value : "null";

            // Calendar型は見やすいフォーマットに
            if (Calendar.class.isAssignableFrom(value.getClass())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
                value = sdf.format(((Calendar) value).getTime());
            }

            sb.append(propertyName).append("=").append(value);
        });
        sb.append("}");

        sb.insert(0, "{");
        sb.insert(0, bean.getClass().getSimpleName());

        return sb.toString();
    }
}

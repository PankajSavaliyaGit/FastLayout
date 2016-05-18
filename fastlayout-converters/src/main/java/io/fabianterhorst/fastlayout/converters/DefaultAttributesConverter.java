package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 18.05.16.
 */
public class DefaultAttributesConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttributeValue(Object attributeValue, String attributeName) {
        switch (attributeName) {
            case "android:orientation":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:gravity":
                return super.onConvertLayoutAttribute("Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:layout_gravity":
                return onConvertLayoutAttribute("Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:background":
                String value = String.valueOf(attributeValue);
                if (value.startsWith("?attr/")) {
                    return super.onConvertLayoutAttribute("LayoutUtils.getAttrDrawable(getContext(), R.attr." + value.replace("?attr/", "") + ")", attributeName, false);
                }
        }
        return null;
    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:layout_gravity":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, attribute(attributeName.replace("android:layout_", ""), attributeValue), false);
        }
        return super.onConvertLayoutAttribute(attributeValue, attributeName, isString);
    }
}
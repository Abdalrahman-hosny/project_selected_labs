package com.mycompany.restaurantmanagement.factory.factory1;

// Factory Design Pattern
// استخدام تصميم الـ Factory لإنشاء كائنات من أنواع مختلفة بناءً على المدخلات دون الحاجة إلى تحديد فئات الكائنات بشكل صريح.

public class MenuItemFactory {

    // الطريقة الثابتة (static method) لإنشاء كائن من نوع MenuItemInterface بناءً على النوع المُمرر.
    public static MenuItemInterface createMenuItem(String type) {
        
        // التبديل بناءً على النوع المُمرر وتحويله إلى حروف صغيرة لتجنب تأثير الحروف الكبيرة أو الصغيرة.
        switch (type.toLowerCase()) {
        
            // إذا كان النوع هو "appetizer" (مقبلات)، قم بإنشاء كائن من فئة Appetizer.
            case "appetizer":
                return new Appetizer();
                
            // إذا كان النوع هو "main course" (الطبق الرئيسي)، قم بإنشاء كائن من فئة MainCourse.
            case "main course":
                return new MainCourse();
                
            // إذا كان النوع هو "dessert" (تحلية)، قم بإنشاء كائن من فئة Dessert.
            case "dessert":
                return new Dessert();
                
            // إذا كان النوع غير صحيح أو غير معروف، رمي استثناء مع رسالة توضح ذلك.
            default:
                throw new IllegalArgumentException("Invalid menu item type: " + type);
        }
    }
}


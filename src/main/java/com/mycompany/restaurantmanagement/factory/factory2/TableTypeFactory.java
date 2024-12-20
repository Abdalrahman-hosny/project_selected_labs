package com.mycompany.restaurantmanagement.factory.factory2;

// Factory Design Pattern
// استخدام تصميم الـ Factory لإنشاء كائنات من أنواع مختلفة بناءً على المدخلات دون الحاجة إلى تحديد فئات الكائنات بشكل صريح.

public class TableTypeFactory {
    
    // الطريقة الثابتة (non-static method) لإنشاء كائن من نوع TableType بناءً على النوع المُمرر.
    public TableType createTableType(String type) {
        
        // التبديل بناءً على النوع المُمرر وتحويله إلى حروف صغيرة لتجنب تأثير الحروف الكبيرة أو الصغيرة.
        switch (type.toLowerCase()) {
        
            // إذا كان النوع هو "regular" (طاولة عادية)، قم بإنشاء كائن من فئة RegularTable.
            case "regular":
                return new RegularTable();
                
            // إذا كان النوع هو "vip" (طاولة VIP)، قم بإنشاء كائن من فئة VIPTable.
            case "vip":
                return new VIPTable();
                
            // إذا كان النوع هو "outdoor" (طاولة خارجية)، قم بإنشاء كائن من فئة OutdoorTable.
            case "outdoor":
                return new OutdoorTable();
                
            // إذا كان النوع غير صحيح أو غير معروف، رمي استثناء مع رسالة توضح ذلك.
            default:
                throw new IllegalArgumentException("Invalid table type: " + type);
        }
    }
}


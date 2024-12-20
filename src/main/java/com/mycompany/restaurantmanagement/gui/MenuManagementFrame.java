/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantmanagement.gui;

import com.mycompany.restaurantmanagement.factory.factory1.MenuItemFactory;
import com.mycompany.restaurantmanagement.entity.MenuItem;
import com.mycompany.restaurantmanagement.singleton.singleton3.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MenuManagementFrame extends JFrame {

    public MenuManagementFrame() {
        setTitle("Menu Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel typeLabel = new JLabel("Menu Item Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Appetizer", "Main course", "Dessert"}); // Dropdown for menu item type

        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JButton addButton = new JButton("Add Menu Item");

        add(typeLabel);
        add(typeComboBox);
        add(nameLabel);
        add(nameField);
        add(priceLabel);
        add(priceField);
        add(new JLabel());
        add(addButton);

        addButton.addActionListener(e -> {

     // الكود ده بيعبر عن شاشة لإدارة عناصر المنيو باستخدام Java Swing.
    // فيه استخدام لكذا مبدأ من مبادئ البرمجة النظيفة والـ Design Patterns، هشرح دورهم واحدة واحدة.

    // 1. **Factory Design Pattern**:
   // هنا إحنا بنستخدم Factory Pattern في `MenuItemFactory.createMenuItem(type)`
  // الهدف من الـ Factory إنه يبسط عملية إنشاء الكائنات بناءً على النوع اللي اختاره المستخدم.
  // بدل ما نكتب شرطيات كتير (if/else) عشان نحدد نوع الـ MenuItem، المصنع (Factory) بيعمل كل الشغل ده لوحده.
  // ده بيخلي الكود أسهل في الصيانة والتطوير.

            try {
                // بنختار النوع اللي المستخدم اختاره من القائمة المنسدلة
                String type = (String) typeComboBox.getSelectedItem();
                String name = nameField.getText(); // اسم العنصر اللي المستخدم كتبه
                Double price = Double.parseDouble(priceField.getText()); // نحول السعر المدخل لرقم Double

                // نتأكد إن الاسم والسعر مش فاضيين
                if (price == null || name.isBlank()) {
                    throw new IllegalArgumentException("you should enter price and name");
                }

                try (Connection connection = DatabaseConnection.getConnection()) {
                    // هنا بقى شغل الـ Factory اللي بيعمل كائن بناءً على النوع المختار
                    assert type != null;
                    MenuItem menuItem = MenuItemFactory.createMenuItem(type).prepare();
                    menuItem.setName(name); // بنحط الاسم اللي المستخدم كتبه
                    menuItem.setPrice(price); // بنحط السعر اللي المستخدم كتبه

                    // بنكتب استعلام SQL لإضافة العنصر لقاعدة البيانات
                    String query = "INSERT INTO MenuItems (type, name, price) VALUES (?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, menuItem.getType()); // النوع
                    statement.setString(2, menuItem.getName()); // الاسم
                    statement.setDouble(3, menuItem.getPrice()); // السعر
                    statement.executeUpdate(); // ننفذ الاستعلام

                    // لو كله تمام، نظهر رسالة نجاح للمستخدم
                    JOptionPane.showMessageDialog(this, "Menu Item Added Successfully!");
                }
            } catch (NumberFormatException ex) {
                // لو السعر المدخل مش رقم، نظهر رسالة خطأ
                JOptionPane.showMessageDialog(this, "Error: Invalid price format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // لو فيه أي خطأ آخر، نطبعه ونظهر رسالة عامة للمستخدم
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: Unable to add menu item.");
            }

// 2. **Exception Handling**:
// الكود بيتعامل مع أكتر من نوع من الأخطاء:
// - NumberFormatException: لو المستخدم دخل رقم غير صالح في خانة السعر.
// - IllegalArgumentException: لو الحقول ناقصة أو مش متكملة.
// - Exception عام لأي حاجة غير متوقعة.

// 3. **Separation of Concerns**:
// - شاشة الواجهة (GUI) مسئولة عن التعامل مع الإدخال والإخراج.
// - المصنع (Factory) مسئول عن إنشاء الكائنات.
// - قاعدة البيانات مسئولية الكود اللي بيتعامل مع `Connection`.

// 4. **Validation**:
// قبل ما نضيف البيانات، بنتأكد إن المدخلات كلها صحيحة عشان ما يحصلش مشاكل في قاعدة البيانات.


        });

        setVisible(true);
    }
}

package com.company.checkcompositionoffragments.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Product;

@UiController("checkcompositionoffragments_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}
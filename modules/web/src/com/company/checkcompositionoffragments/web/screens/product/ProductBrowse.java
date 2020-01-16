package com.company.checkcompositionoffragments.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.checkcompositionoffragments.entity.Product;

@UiController("checkcompositionoffragments_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}
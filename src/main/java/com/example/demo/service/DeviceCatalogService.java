
// package com.example.demo.service;

// import com.example.demo.model.DeviceCatalogItem;
// import java.util.List;

// public interface DeviceCatalogService {

//     DeviceCatalogItem createItem(DeviceCatalogItem item);

//     DeviceCatalogItem updateActiveStatus(Long id, boolean active);

//     List<DeviceCatalogItem> getAllItems();
// }

package com.example.demo.service;

import com.example.demo.model.DeviceCatalogItem;
import java.util.List;

public interface DeviceCatalogService {
    DeviceCatalogItem createItem(DeviceCatalogItem item);
    DeviceCatalogItem updateActiveStatus(Long id, Boolean active);
    List<DeviceCatalogItem> getAllItems();
}

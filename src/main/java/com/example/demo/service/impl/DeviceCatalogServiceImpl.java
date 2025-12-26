package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {
    private final DeviceCatalogItemRepository deviceRepo;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (item.getMaxAllowedPerEmployee() != null && item.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("maxAllowedPerEmployee must be greater than 0");
        }
        if (deviceRepo.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("Device code already exists");
        }
        return deviceRepo.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, Boolean active) {
        DeviceCatalogItem item = deviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        item.setActive(active);
        return deviceRepo.save(item);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return deviceRepo.findAll();
    }
}
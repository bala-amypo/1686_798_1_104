package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DeviceCatalogItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    private final DeviceCatalogItemRepository repository;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {

        if (repository.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("Rule code");
        }

        if (item.getMaxAllowedPerEmployee() == null ||
            item.getMaxAllowedPerEmployee() < 1) {
            throw new BadRequestException("maxAllowedPerEmployee");
        }

        return repository.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        item.setActive(active);
        return repository.save(item);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return repository.findAll();
    }
}

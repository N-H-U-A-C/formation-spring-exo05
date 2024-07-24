package dev.cb.business.service;

import dev.cb.business.domain.Furniture;
import dev.cb.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAll() {
        return furnitureRepository.findAll();
    }

    public Optional<Furniture> getById(UUID id) {
        return furnitureRepository.findById(id);
    }

    public Furniture create(Furniture furniture) {
        furniture.setId(UUID.randomUUID());
        return furnitureRepository.save(furniture);
    }

    public Furniture update(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public void deleteById(UUID id) {
        furnitureRepository.deleteById(id);
    }
}

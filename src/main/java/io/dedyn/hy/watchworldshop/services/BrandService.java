package io.dedyn.hy.watchworldshop.services;

import io.dedyn.hy.watchworldshop.entities.Brand;
import io.dedyn.hy.watchworldshop.repositories.BrandRepository;
import io.dedyn.hy.watchworldshop.utils.SlugifyUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public List<Brand> findAll() {
        return brandRepository.findAll(Sort.by("name"));
    }

    public Brand findById(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand save(Brand brand) {
        brand.setSlug(SlugifyUtil.slugify(brand.getName()));
        return brandRepository.save(brand);
    }

    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }

    public boolean isUniqueName(Brand brand) {
        Brand dbBrand = brandRepository.findFirstBySlug(SlugifyUtil.slugify(brand.getName()));
        if (dbBrand == null) return true;
        if (brand.getId() == null) return false;
        return brand.getId().equals(dbBrand.getId());
    }
}

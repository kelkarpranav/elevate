package app;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A very noddy implementation of the {@see BookRepository} interface.
 */
@Component
class DummyBrandRepository implements BrandRepository {
  private final Map<Integer, Brand> brands = new ConcurrentHashMap<Integer, Brand>();

  @Override
  public Brand findById(Integer id) {
    return this.brands.get(id);
  }

  @Override
  public List<Brand> findAll() {
    List<Brand> brands = new ArrayList<Brand>(this.brands.values());
    Collections.sort(brands, new Comparator<Brand>() {
      @Override
      public int compare(Brand o1, Brand o2) {
        return o1.getId() - o2.getId();
      }
    });
    return brands;
  }

  @Override
  public Brand save(Brand brand) {
    if (brand.getId() == null) {
    	brand.setId(nextId());
    }
    this.brands.put(brand.getId(), brand);
    return brand;
  }

  @Override
  public void delete(Integer id) {
    this.brands.remove(id);
  }

  private Integer nextId() {
    if (this.brands.isEmpty()) {
      return 1;
    } else {
      return Collections.max(this.brands.keySet()) + 1;
    }
  }
}

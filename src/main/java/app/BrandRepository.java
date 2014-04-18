package app;

import java.util.List;

/**
 * Basic repository for CRUD operations on {@see Book books}.
 */
public interface BrandRepository {

  /**
   * Find a <code>Book</code> by ID.
   *
   * @param id the ID of the book.
   * @return the book, or <code>null</code> if no book is found.
   */
  Brand findById(Integer id);

  List<Brand> findAll();

  Brand save(Brand brand);

  void delete(Integer id);
}

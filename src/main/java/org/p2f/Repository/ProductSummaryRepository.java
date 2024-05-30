package org.p2f.Repository;


import org.p2f.ReadSide.ProductSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ProductSummaryRepository extends JpaRepository<ProductSummary, String> {
    List<ProductSummary> findAllByDescriptionContaining(String Description);

}

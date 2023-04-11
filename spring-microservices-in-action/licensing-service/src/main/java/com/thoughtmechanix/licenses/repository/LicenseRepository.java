package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.License;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 胡磊
 * @since 2023/4/10 17:44
 */
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {
  List<License> findAllByOrganizationId(String organizationId);
  License findByOrganizationIdAndLicenseId(String organizationId,String licenseId);
}

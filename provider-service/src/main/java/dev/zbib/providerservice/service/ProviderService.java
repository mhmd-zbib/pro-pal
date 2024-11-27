package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.enums.ServiceType;
import dev.zbib.providerservice.model.request.RegisterProviderRequest;
import dev.zbib.providerservice.model.response.DetailsListResponse;
import dev.zbib.providerservice.model.response.ProviderListResponse;
import dev.zbib.providerservice.model.response.UserListResponse;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.providerservice.specification.ProviderSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.zbib.providerservice.model.mapper.DetailsMapper.toDetailsListResponse;
import static dev.zbib.providerservice.model.mapper.ProviderMapper.toProvider;
import static dev.zbib.providerservice.model.mapper.ProviderMapper.toProviderListResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserClient userClient;
    private final UserService userService;


    public void registerProvider(
            Long id,
            RegisterProviderRequest request) {
        Provider provider = toProvider(id, request);
        providerRepository.save(provider);
    }

    public Provider getProviderById(Long userId) {
        return providerRepository.findById(userId)
                .orElse(null);
    }

    public void deleteProviderByUserId(Long userId) {
        Provider provider = getProviderById(userId);
        if (provider != null) {
            providerRepository.delete(provider);
        }
    }

    public Page<ProviderListResponse> getProviderPage(
            ServiceType serviceType,
            Boolean available,
            Double hourlyRate,
            String serviceArea,
            Pageable pageable) {
        var specification = ProviderSpecification.createFilter(serviceType, available, hourlyRate, serviceArea);
        Page<Provider> providerPage = providerRepository.findAll(specification, pageable);
        List<UserListResponse> users = userService.getUserDetailsForProviders(providerPage.getContent());
        List<ProviderListResponse> providerList = toProviderListResponse(
                providerPage.getContent(),
                users);

        return new PageImpl<>(providerList, pageable, providerPage.getTotalElements());
    }

    public List<DetailsListResponse> getDetailListById(List<Long> ids) {
        List<Provider> providerList = providerRepository.findProvidersByIdIn(ids);
        return toDetailsListResponse(providerList);
    }
}

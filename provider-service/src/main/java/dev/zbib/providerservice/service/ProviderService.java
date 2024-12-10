package dev.zbib.providerservice.service;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.entity.Provider;
import dev.zbib.providerservice.repository.ProviderRepository;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.enums.UserRoles;
import dev.zbib.shared.exception.ProviderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.zbib.providerservice.mapper.ProviderMapper.toProvider;
import static dev.zbib.providerservice.mapper.ProviderMapper.toProviderResponse;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final UserService userService;

    public void createProvider(CreateProviderRequest req) {
        if (providerRepository.existsById(req.getId())) {
            throw ProviderException.alreadyExist();
        }
        Provider provider = toProvider(req);
        providerRepository.save(provider);
        userService.setUserRole(req.getId(), UserRoles.PROVIDER);
    }

    public ProviderResponse getProviderById(Long id) {
        DetailsResponse detailsResponse = getProviderDetailsById(id);
        UserResponse userResponse = userService.getUser(id);
        return toProviderResponse(userResponse, detailsResponse);
    }

    private DetailsResponse getProviderDetailsById(Long id) {
        return providerRepository.findDetailsById(id)
                .orElseThrow(ProviderException::notFound);
    }




}

package shop.service;

import shop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.security.CustomerDetails;

@Service
@RequiredArgsConstructor
public class DomainCustomerDetailsService implements UserDetailsService {


    private final CustomerRepository customerRepository;

//    public DomainCustomerDetailsService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        shop.model.Customer customer = customerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        return (CustomerDetails) org.springframework.security.core.userdetails.User
//                .withUsername(user.getName())
//                .password(user.getPass())
////                .roles(user.getRole().replace("ROLE_", ""))
//                .build();
        return new CustomerDetails(customer);
    }


}
package com.eapp.myclubmanager.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csrf")
public class CsrfController {

    @GetMapping("/token")
    public CsrfToken csrfToken(CsrfToken csrfToken){
        return csrfToken;
    }
}

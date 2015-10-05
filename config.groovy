environments {
    dev {
        spring.profiles.active = 'externalStubbed'
    }

    test {
        spring.profiles.active = 'externalFake'
    }
    prod {
        spring.profiles.active = 'external'
    }
}
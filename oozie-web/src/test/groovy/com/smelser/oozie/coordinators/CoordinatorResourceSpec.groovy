package com.smelser.oozie.coordinators

import spock.lang.Specification


public class CoordinatorResourceSpec extends Specification {

    def "test"(){
        when:
            new CoordinatorResource(null).getCoordinators();
        then:
            true;
    }
}
package test.command.company

import grails.validation.Validateable

class DivisionsCommand implements  Validateable{
    static final List<Integer> VALID_DEPARTMENT_CODES = [1, 2, 3, 4]
    static final List<String>  VALID_DEPARTMENTS      = ['sales', 'engineering', 'marketing']

    String name
    Map<String,Integer> departmentCodes


    static constraints = {
        name size: 4..255
        departmentCodes validator: {Map<String,Integer>departmentCodes, DivisionsCommand command ->
            List<Integer> codes = departmentCodes.values() as List
            List<String> departments = departmentCodes.keySet() as List

            if(!VALID_DEPARTMENT_CODES.containsAll(codes)){
                return 'invalid.department.code'
            }

            if(!VALID_DEPARTMENTS.containsAll(departments)){
                return 'invalid.department'
            }
    }   }
}

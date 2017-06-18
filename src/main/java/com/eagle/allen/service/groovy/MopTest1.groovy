package com.eagle.allen.service.groovy

/**
 * Created by guobingwei on 17/3/31.
 */
class MopTest1 extends GroovyTestCase {



    void testInterceptedMethodCallonPOJO() {
        def val = new Integer(3);
//        Integer.metaClass.toString() = {-> 'intercepted'}
        assertEquals "intercepted", val.toString()
    }

    public void testIneterceptableCalled() {
        def obj = new AnInterceptaable();

        assertEquals('interceptable', )
    }

    class AnInterceptaable implements GroovyInterceptable {
        def existingMethod() {}
        def invokeMethod(String name, args) {'intercepted'}
    }
}

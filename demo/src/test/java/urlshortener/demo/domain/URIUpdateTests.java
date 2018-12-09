package urlshortener.demo.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class URIUpdateTests {

    @Test
    public void testOK(){
        URIUpdate uriUpdate1 = new URIUpdate();
        URIUpdate uriUpdate2 = uriUpdate1.newName("abc");
        URIUpdate uriUpdate3 = (URIUpdate) uriUpdate2.hashpass("abcd");

        assertEquals(uriUpdate3, uriUpdate1);
        assertEquals(uriUpdate3, uriUpdate2);
        assertEquals("abc", uriUpdate1.getNewName());
        assertEquals("abcd", uriUpdate1.getHashpass());
    }

    @Test
    public void testToString(){
        URIUpdate uriUpdate1 = (URIUpdate) new URIUpdate().newName("abc").hashpass("abcd");
        assertEquals("class URIUpdate {\n    newName: abc\n    hashpass: abcd\n}", uriUpdate1.toString());
    }

}

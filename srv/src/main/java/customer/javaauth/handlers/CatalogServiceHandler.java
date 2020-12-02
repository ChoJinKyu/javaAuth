package customer.javaauth.handlers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.feature.xsuaa.XsuaaUserInfo;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Books;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @Autowired
    XsuaaUserInfo xsuaaUserInfo;
    

    @After(event = CdsService.EVENT_READ)
	public void discountBooks(List<Books> books) {
        String userInfoStr = "";
        userInfoStr = ("toString : " + xsuaaUserInfo.toString());
        userInfoStr = userInfoStr + ("name : " + xsuaaUserInfo.getName());

        //userInfoStr = userInfoStr + " extUserInfo.getName() : " + extUserInfo.getName();
        //userInfoStr = userInfoStr + " extUserInfo.getName_eng() : " + extUserInfo.getName_eng();

        /*
        ExtUserInfo extUserInfo22 = (ExtUserInfo) xsuaaUserInfo.getAdditionalAttribute("session");
        userInfoStr = userInfoStr + " extUserInfo22.getName() : " + extUserInfo22.getName();
        userInfoStr = userInfoStr + " extUserInfo22.getName() : " + extUserInfo22.getName_eng();
        */        
        
        Map<String, List<String>> userInfoAttr = xsuaaUserInfo.getAttributes();
        for ( String key : userInfoAttr.keySet() ) {
            userInfoStr = userInfoStr + (" key : " + key + " value : ");

            for(int i =0; i < userInfoAttr.get(key).size(); i++){
                userInfoStr = userInfoStr + userInfoAttr.get(key).get(i);
            }
        }        

        
        Map<String, Object> userInfo = xsuaaUserInfo.getAdditionalAttributes();

        for ( String key : userInfo.keySet() ) {
            userInfoStr = userInfoStr + (" key : " + key + " value : " + userInfo.get(key));
        }
        

        /*
        ExtUserInfo extUserInfo = (ExtUserInfo) xsuaaUserInfo.getAdditionalAttribute("session");
        userInfoStr = userInfoStr + (" session getName : "  + (extUserInfo.getName()));
        userInfoStr = userInfoStr + (" session getName_eng : "  + (extUserInfo.getName_eng()));
        userInfoStr = userInfoStr + (" session getEmail : "  + (extUserInfo.getEmail()));
        userInfoStr = userInfoStr + (" session getDesc : "  + (extUserInfo.getDesc()));
        String attr1 = (String) xsuaaUserInfo.getAdditionalAttribute("Attr1");
        String attr2 = (String) xsuaaUserInfo.getAdditionalAttribute("Attr2");
        String attr3 = (String) xsuaaUserInfo.getAdditionalAttribute("Attr3");
        userInfoStr = userInfoStr + (" attr1 : "  + attr1);
        userInfoStr = userInfoStr + (" attr2 : "  + attr2);
        userInfoStr = userInfoStr + (" attr3 : "  + attr3);
        */
        

        for ( Books book : books ) {
            book.setTitle(userInfoStr);
        }

	}

}
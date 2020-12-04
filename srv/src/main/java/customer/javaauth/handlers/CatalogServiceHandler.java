package customer.javaauth.handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.feature.xsuaa.XsuaaUserInfo;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.request.ParameterInfo;
import com.sap.cds.services.request.UserInfo;

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

        Map<String, List<String>> userInfoAttr = xsuaaUserInfo.getAttributes();
        userInfoStr = userInfoStr + ("######################## getAttributes ");
        for (String key : userInfoAttr.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : ");

            for (int i = 0; i < userInfoAttr.get(key).size(); i++) {
                userInfoStr = userInfoStr + userInfoAttr.get(key).get(i);
            }
        }

        Map<String, Object> userInfo = xsuaaUserInfo.getAdditionalAttributes();
        userInfoStr = userInfoStr + ("######################## getAdditionalAttributes ");
        for (String key : userInfo.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : " + userInfo.get(key));
        }

        Set<String> roles = xsuaaUserInfo.getRoles();
        userInfoStr = userInfoStr + ("######################## getRoles ");
        for(String str : roles){
            userInfoStr = userInfoStr + (" role : " + str);
        }
        
        Set<String> usattr = xsuaaUserInfo.getUnrestrictedAttributes();
        userInfoStr = userInfoStr + ("######################## getUnrestrictedAttributes ");
        for(String str : usattr){
            userInfoStr = userInfoStr + (" usattr : " + str);
        }

/*        
        Map<String, List<String>> getUserAttributes = xsUaaToken.getUserAttributes();
        userInfoStr = userInfoStr + ("######################## getUserAttributes ");
        for (String key : getUserAttributes.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : ");

            for (int i = 0; i < getUserAttributes.get(key).size(); i++) {
                userInfoStr = userInfoStr + getUserAttributes.get(key).get(i);
            }
        }

        Map<String, List<String>> getSystemAttributes = xsUaaToken.getSystemAttributes();
        userInfoStr = userInfoStr + ("######################## getSystemAttributes ");
        for (String key : getSystemAttributes.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : ");

            for (int i = 0; i < getSystemAttributes.get(key).size(); i++) {
                userInfoStr = userInfoStr + getSystemAttributes.get(key).get(i);
            }
        }

        Map<String, Object> getExtensionAttributes = xsUaaToken.getExtensionAttributes();
        userInfoStr = userInfoStr + ("######################## getExtensionAttributes ");
        for (String key : getExtensionAttributes.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : " + getExtensionAttributes.get(key));
        }
        Map<String, Object> getAdditionalAttributes = xsUaaToken.getAdditionalAttributes();
        userInfoStr = userInfoStr + ("######################## getAdditionalAttributes ");
        for (String key : getAdditionalAttributes.keySet()) {
            userInfoStr = userInfoStr + (" key : " + key + " value : " + getAdditionalAttributes.get(key));
        }
*/

        for ( Books book : books ) {
            book.setTitle(userInfoStr);
        }


        
	}

}
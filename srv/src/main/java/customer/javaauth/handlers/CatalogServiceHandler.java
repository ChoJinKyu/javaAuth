package customer.javaauth.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.Result;
import com.sap.cds.feature.xsuaa.XsuaaUserInfo;
import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.ql.cqn.CqnReference.Segment;
import com.sap.cds.reflect.CdsEntity;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.ql.cqn.AnalysisResult;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnStatement;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsDeleteEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.request.ParameterInfo;
import com.sap.cds.services.request.UserInfo;

import cds.gen.catalogservice.*;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @Autowired
    XsuaaUserInfo xsuaaUserInfo;

/*    
    @Autowired
    @Qualifier(CatalogService_.CDS_NAME)
    private CdsService catalogService;

    @On(event = CdsService.EVENT_DELETE, entity = Books_.CDS_NAME)
    public void ondiscountBooksDelete(CdsDeleteEventContext context) {

        Iterable<Map<String, Object>> aa = context.getCqnValueSets();

        CdsModel cdsModel = context.getModel();
        CqnAnalyzer cqnAnalyzer = CqnAnalyzer.create(cdsModel);
        CqnStatement cqn = context.getCqn();
        AnalysisResult result = cqnAnalyzer.analyze(cqn.ref());

        CdsEntity view = result.rootEntity();
        CdsEntity view2 = result.targetEntity();

        Map<String, Object> filterValues = result.targetValues();

        Map<String, Object> rootKeys = result.rootKeys();
        Integer itemId = (Integer) rootKeys.get("ID");  

        Map<String, Object> targetKeys  = result.targetKeys();

        Integer itemId2 = (Integer) targetKeys.get("ID");  


        Map<String, Object> bb = aa.iterator().next();

        while(aa.iterator().hasNext()){
            Books book = Books.create();
            book.setId(11);
            CqnDelete delete = Delete.from(Books_.CDS_NAME).matching(book);
            context.getService().run(delete);
        }
        
        context.setCompleted();

    }
*/
    /*
    @On(event = CdsService.EVENT_UPDATE)
    public void ondiscountBooksUpdate(CdsUpdateEventContext context) { 
        //context.getCqnValueSets()

    }
    */

/*    
    @On(event = CdsService.EVENT_CREATE, entity = BooksView2_.CDS_NAME)
    public void ondiscountBooksCreate(CdsCreateEventContext context) { 
        CdsModel cdsModel = context.getModel();
        CqnAnalyzer cqnAnalyzer = CqnAnalyzer.create(cdsModel);
        CqnStatement cqn = context.getCqn();
        AnalysisResult result = cqnAnalyzer.analyze(cqn.ref());

        
        List<BooksView2> v_results = new ArrayList<BooksView2>();
        BooksView2 book = BooksView2.create();
        book.setId(10000);
        book.setTitle("title");
        book.setLanguageCd("KO");
        v_results.add(book);
        context.setResult(v_results);
        context.setCompleted();

    }
*/    


    @After(event = CdsService.EVENT_READ)
    public void discountBooks(List<Books> books) { 
        String userInfoStr = "";
        userInfoStr = ("toString : " + xsuaaUserInfo.toString());

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
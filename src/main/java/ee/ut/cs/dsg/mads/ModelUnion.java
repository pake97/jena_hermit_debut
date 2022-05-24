package it.polimi.ke.jena.complete;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelUnion{

    public static void main(String[] args) throws FileNotFoundException {
        try{
        Model modelEL = ModelFactory.createDefaultModel().read("./src/main/resources/clevr_el.ttl");
        Model modelQL = ModelFactory.createDefaultModel().read("./src/main/resources/clevr_ql.ttl");
        Model modelRL = ModelFactory.createDefaultModel().read("./src/main/resources/clevr_rl.ttl");

        File child = new File(args[0]);
        System.out.println(child.getName());

        
                
                Model model2 = ModelFactory.createDefaultModel().read(child.getAbsolutePath());
                final Model unionEL = ModelFactory.createUnion(modelEL, model2);
                final Model unionQL = ModelFactory.createUnion(modelQL, model2);
                final Model unionRL = ModelFactory.createUnion(modelRL, model2);
                

                unionEL.write(new FileOutputStream(new File("./src/main/resources/dataset/train/el/"+child.getName())));
                unionQL.write(new FileOutputStream(new File("./src/main/resources/dataset/train/ql/"+child.getName())));
                unionRL.write(new FileOutputStream(new File("./src/main/resources/dataset/train/rl/"+child.getName())));
                index++;
        
    }
        catch(java.lang.NoClassDefFoundError e){
            System.out.println(e);
        }
    }
}
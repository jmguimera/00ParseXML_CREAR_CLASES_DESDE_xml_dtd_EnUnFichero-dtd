/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseXMLfichero;
import generated.*;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author Fermin Velez
 */
public class ParseXMLfichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
      
        Root documento;
        int i;
        Object obj;
        
         try {
           JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller convertidorDeXMLToObjecto = context.createUnmarshaller();
            
              // partir de un documento XML obtenemos el objeto.
               documento = (Root) convertidorDeXMLToObjecto.unmarshal(new File("localidad_38043.xml")); // solo le decimos donde esta el fichero
              
        

 //Mostramos por linea de comandos el objeto Java obtenido 

         
            System.out.println("\nObjeto reconstruido desde fichero formato XML:\n\n");
            
        
           for ( i=0; i < documento.getOrigenOrElaboradoOrNombreOrProvinciaOrPrediccion().size();i++)
            {
                obj = documento.getOrigenOrElaboradoOrNombreOrProvinciaOrPrediccion().get(i);
              //System.out.println("Posicion: "+i+" "+obj.toString());  
               if ( obj.getClass()== Prediccion.class)  // buscar el objeto del tipo que necesitamos
               {
                    Prediccion p=(Prediccion) obj; // en lka posicion 4 graba un objeto tipo Prediccion
                   List<Dia> dias= p.getDia();
                    for (i=0; i< dias.size(); i++)  verUnDia(dias.get(i));
               }
            }                                                                                                  
           
        }
        
        catch (JAXBException ex) {  System.out.println("JAXBE : "+ex.getMessage());  }
            
            
        }
    
    static void verUnDia( Dia d)
    {
        
        System.out.print (d.getFecha()+"  :  ");
        
        Object obj;
        int i;
        
         for (i=0; i < d.getProbPrecipitacionOrCotaNieveProvOrEstadoCieloOrVientoOrRachaMaxOrTemperaturaOrSensTermicaOrHumedadRelativaOrUvMax().size() ; i++)
         {
             obj=d.getProbPrecipitacionOrCotaNieveProvOrEstadoCieloOrVientoOrRachaMaxOrTemperaturaOrSensTermicaOrHumedadRelativaOrUvMax().get(i); 
             if ( obj.getClass()== Temperatura.class) 
             {   
                 Temperatura t = (Temperatura) obj;              
                 Maxima max= (Maxima)  t.getMaximaOrMinimaOrDato().get(0);
                 Minima min=(Minima) t.getMaximaOrMinimaOrDato().get(1);
                System.out.println ("Temperatura minima : "+ min.getvalue()+" Maxima : "+max.getvalue());
             }   
        }
    }
}
    
    
    
    


package com.siprogra.controlador;

import com.siprogra.modelo.Parametro;
import com.siprogra.modelo.Productodetrabajo;
import com.siprogra.DAO.ParametroFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author jalber
 */
@ManagedBean
@SessionScoped
public class ArchivosController implements Serializable 
{
    @EJB
    private ParametroFacade parametroEJB;
    private Parametro anteproyecto;   
    private UploadedFile archivo;
    private boolean botonSubir;
    private StreamedContent file1;
    private String rutaAbsoluta;
    
    public ArchivosController() 
    {
        this.botonSubir=true; 
        this.rutaAbsoluta="/home/jalber/NetBeansProjects/Siprogra_prueba/web/resources/archivosUploads/";
    }
    public UploadedFile getArchivo() 
    {
        return archivo;
    }
    public void setArchivo(UploadedFile archivo) 
    {
        this.archivo = archivo;        
    } 
    public void uploadAnteproyecto() 
    {   
        System.out.println("entro..................");  
        System.out.println(this.archivo.getFileName());
        System.out.println("enertd..................");
        if(!parametroEJB.buscarPorProdutoTrabajoId(BigDecimal.valueOf(2)))
        {
           System.out.println("entro al if..................");
           this.anteproyecto= new Parametro();
           System.out.println("hasta aqui vamos bien..................");
           BigInteger bg=parametroEJB.maximoParmetroID();           
           bg=bg.add(new BigInteger("2"));
           System.out.println("valor del id: "+bg.intValue());           
           this.anteproyecto.setParid(new BigDecimal(bg));
           this.anteproyecto.setParnombre(this.archivo.getFileName());
           Productodetrabajo p=new Productodetrabajo();
           p.setProid(BigDecimal.valueOf(2));
           this.anteproyecto.setPartipo("PDF");
           this.anteproyecto.setProid(p); 
           System.out.println("ruta: "+this.rutaAbsoluta);
           try 
           {
               InputStream input=archivo.getInputstream();
               System.out.println("ruta en el try: "+this.rutaAbsoluta);
               this.guardarArchivo(this.archivo.getFileName(), input);
               this.parametroEJB.create(this.anteproyecto);
           }
           catch (IOException e) 
            {
                e.printStackTrace();
            }          
        }
        else
        {
            this.anteproyecto=this.parametroEJB.buscarRetornarPorProdutoTrabajoId(BigDecimal.valueOf(2)).get(0);
        }
    }
    
    private void guardarArchivo(String filename, InputStream in)
    {
        System.out.println("Nombre que llega a guardar: "+filename);
       try 
       { 
           System.out.println("ruta en guardar: "+this.rutaAbsoluta);
            OutputStream out = new FileOutputStream(new File(this.rutaAbsoluta + filename));              
            int read = 0;
            byte[] bytes = new byte[1024];              
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, bytes.length);
            }
            System.out.println("Escribio el archivo");
            in.close();
            out.flush();
            out.close();
       } catch (IOException e)
       {
            System.out.println(e.getMessage());
       } 
    }
    
    public Parametro getAnteproyecto() 
    {
        return anteproyecto;
    }

    public void setAnteproyecto(Parametro anteproyecto)
    {
        this.anteproyecto = anteproyecto;
    }
    
    public StreamedContent prepDownload() throws Exception 
    {
        StreamedContent download = new DefaultStreamedContent();
        File file = new File("/home/jalber/Killer_Instinct_Gold.png");
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
        System.out.println("PREP = " + download.getName());
        return download;
    }
    public boolean isBotonSubir() 
    {
        return botonSubir;
    }
    public void setBotonSubir(boolean botonSubir) 
    {
        this.botonSubir = botonSubir;
    }
    public StreamedContent getFile1() 
    {
        return file1;
    }
    
    public void cargarArchivo(FileUploadEvent event)
    {     
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.archivo=event.getFile();
        try {
            if(archivo.getInputstream()==null)
            {
                System.out.println("No cargo el inputStream");
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("en Cargar Archivo: "+this.archivo.getFileName());
        this.botonSubir=false;        
        requestContext.update("tabaviewgeneal:formularioSubirAnteproyecto");
        requestContext.update("tabaviewgeneal:formularioBotonSubir");   
    }
    
}

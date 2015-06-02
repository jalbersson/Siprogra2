/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.controlador;

import com.siprogra.DAO.*;
import com.siprogra.clases.*;
import com.siprogra.modelo.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author elkin
 */

@ManagedBean
@SessionScoped
public class ProyectoController implements Serializable 
{
    @EJB
    private ProcesodegradoFacade ejbProcesogrado;
    private Procesodegrado objProcesogrado;
    private List<Procesodegrado> lstProcesogrado;
    @EJB
    private UsuarioFacade ejbUsuario;
    private Usuario objUsuario;
    private List<Usuario> lstUsuario;
    @EJB
    private ProductodetrabajoFacade ejbProductotrabajo;
    private Productodetrabajo objProductotrabajo;
    private List<Productodetrabajo> lstProductotrabajos;
    @EJB
    private ContenidoFacade ejbContenido;
    private Contenido objContenido;
    private List<Contenido> lstContenido;
    @EJB
    private ParametroFacade ejbParametro;
    private Parametro objParametro;
    private List<Parametro> lstParametro;
    @EJB
    private RolFacade ejbRol;
    private Rol objRol;
    private List<Rol> lstRol;
    @EJB
    private FlujoFacade ejbFlujo;
    private Flujo objFlujo;
    private List<Flujo> lstFlujo;

    private int cedulaEstudiante;

    private FormatoA formatoA;
    private RevisionIdea actaRevisionIdea;
    private boolean vista;
    private ActaAprobacionAnteproyecto actaAnteproyecto;
    private FormatoG formatoG;

    
    private String titulo;

    //variables envio correo
    private final String miCorreo;
    private final String miContraseña;
    private final String servidorSMTP;
    private final String puertoEnvio;
    private boolean existeMail;
    private String contrasena;
    private String mailReceptor;
    private String asunto;
    private String cuerpo;

    //variables subir archivo
    private final String destino = "/home/jalber/NetBeansProjects/Siprogra/SiProGra/web/resources/pdf/";
    private UploadedFile file;
    private boolean seleccionar = false;
    private String director;
    private List<Usuario> lstEstudiantePro;
    private List<Procesodegrado> lstProyectosDocente;
    private List<Procesodegrado> lstProyectosEvaluador;
    private List<Procesodegrado> lstProyectosJefeDepto;
    private List<Procesodegrado> lstProyectosDepto;
    private List<Procesodegrado> lstProyectosCoordinador;
    private boolean evaluado;
    private FormatoA obtenerFormatoA;
    private String carta;
    private String anteproyecto;
    private boolean ideaAprobada;
    
    public ProyectoController() {
        lstProcesogrado = new ArrayList<>();

        //variables envio correo
        this.existeMail = false;
        this.miCorreo = "proyectospopasoft@gmail.com";
        this.miContraseña = "popasoft123";
        this.servidorSMTP = "smtp.gmail.com";
        this.puertoEnvio = "587";
    }

    public void prepareCrear() {
        objProcesogrado = new Procesodegrado();
        formatoA = new FormatoA();
        objContenido = new Contenido();
        objParametro = new Parametro();
        objProductotrabajo = new Productodetrabajo();
        objUsuario = new Usuario();
        actaAnteproyecto= new ActaAprobacionAnteproyecto();
        formatoG= new FormatoG();
    }

    public void prepareActaRevIdea() {
        objProcesogrado = new Procesodegrado();
        actaRevisionIdea = new RevisionIdea();
        objContenido = new Contenido();
        objParametro = new Parametro();
        objProductotrabajo = new Productodetrabajo();
        objUsuario = new Usuario();
    }

    public void prepareAgregarEstudiante() {
        lstUsuario = ejbUsuario.findAll();
        objUsuario = new Usuario();
    }
    
    public void prepareAgregarAnte() {
        titulo = new String();
        objContenido = new Contenido();
        objParametro = new Parametro();
        objProductotrabajo = new Productodetrabajo();
        objUsuario = new Usuario();
        seleccionar = false;
    }

    public void borrarEstudiante() {

    }
    
    public void agregarAnteproyecto() throws IOException {
        obtenerUsuario();
        objProcesogrado=ejbProcesogrado.buscarPorTitulo(objUsuario.getProcesodegradoList().get(0).getProctitulo());
        objProcesogrado.setProductodetrabajoList(new ArrayList<Productodetrabajo>());
        obtenerProductotrabajo("Anteproyecto terminado gestionado por el estudiante");
        objProcesogrado.getProductodetrabajoList().add(objProductotrabajo);

        ejbProcesogrado.edit(objProcesogrado);

        lstParametro = obtenerParametros(objProductotrabajo.getProid().intValue());

        objParametro = obtenerParametro("TITULO ANTEPROYECTO");//el nombre lo modifica de acuerdo a sus datos en la bd
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(titulo);
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        String rutaArchivo = destino + file.getFileName();
        upload();
        objParametro = obtenerParametro("URL ARCHIVO ANTEPROYECTO");//el nombre lo modifica de acuerdo a sus datos en la bd
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(rutaArchivo);
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        RequestContext.getCurrentInstance().execute("PF('addAntPDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Registro exitoso"));
    }

    public void crearActaRevision() {
        obtenerUsuario();
        objProcesogrado.setProductodetrabajoList(new ArrayList<Productodetrabajo>());
        obtenerProductotrabajo("revision de la idea del proyecto");
        objProcesogrado.getProductodetrabajoList().add(objProductotrabajo);
        //objProcesogrado.setProcestado(new BigInteger(""+4));

        ejbProcesogrado.edit(objProcesogrado);

        lstParametro = obtenerParametros(objProductotrabajo.getProid().intValue());

        objParametro = obtenerParametro("Numero de acta");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(actaRevisionIdea.getNumActa());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Observaciones");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(actaRevisionIdea.getObservaciones());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Resultado");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(actaRevisionIdea.getResultado());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objProcesogrado = null;
        RequestContext.getCurrentInstance().execute("PF('RevisionIdeaDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Revision exitosa"));
    }
    public void crearActaAprobacionAnteproyecto()
    {
        obtenerUsuario();
        objProcesogrado=ejbProcesogrado.buscarPorTitulo("Determinación de Anomalías en Telas por Tratamiento Digital de Imágenes");
        //objProcesogrado.setProductodetrabajoList(new ArrayList<Productodetrabajo>());
        //objProcesogrado.setProcid(new BigDecimal(numeroAleatorio()));
        obtenerProductotrabajo("Acta de resolucion en donde se aprueba el anteproyecto");
        //objProcesogrado.getProductodetrabajoList().add(objProductotrabajo);
        objProcesogrado.setProcestado(new BigInteger(""+3));
        ejbProcesogrado.edit(objProcesogrado);

        lstParametro = obtenerParametros(objProductotrabajo.getProid().intValue());

        objParametro = obtenerParametro("NUMERO ACTA RESOLUCION");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(""+actaAnteproyecto.getNumeroActa());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("FECHA ACTA RESOLUCION");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(actaAnteproyecto.getFechaActa().toString());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        RequestContext.getCurrentInstance().execute("PF('DialogoAprobacionAnteproyecto').hide()");// cambiar al dialogo correspondiente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Registro exitoso"));
    }
    public void crearRemisionFinal()
    {
        obtenerUsuario();
        //objProcesogrado=ejbProcesogrado.buscarPorTitulo("Determinación de Anomalías en Telas por Tratamiento Digital de Imágenes");
        //objProcesogrado.setProductodetrabajoList(new ArrayList<Productodetrabajo>());
        //objProcesogrado.setProcid(new BigDecimal(numeroAleatorio()));
        obtenerProductotrabajo("Formato de remision del trabajo final a la Secretaria General (Formato G)");
        //objProcesogrado.getProductodetrabajoList().add(objProductotrabajo);
        //objProcesogrado.setEstado("Ejecucion del proyecto de grado");
        //ejbProcesogrado.edit(objProcesogrado);

        lstParametro = obtenerParametros(objProductotrabajo.getProid().intValue());

        objParametro = obtenerParametro("TITULO TRABAJO DE GRADO");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getTitulo());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("FECHA Y HORA");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getFechaSustentacion().toString());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("APROBACION (SI O NO)");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getAprobado());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("AJUSTES AL DOCUMENTO (SI O NO)");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getAjustes());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("FECHA FIRMA DOCUMENTO");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getFechaConstancia().toString());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("DIRECTOR");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getDirector());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("JURADO COORDINADOR");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoG.getJuradoCoordinador());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        objParametro = obtenerParametro("NUMERO RECIBO TESORERIA");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(""+formatoG.getNumeroRecibo());
        objContenido.setParid(objParametro);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);
        
        RequestContext.getCurrentInstance().execute("PF('DialgoRemisionTrabajoFinal').hide()");// cambiar al dialogo correspondiente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Registro exitoso"));
    }
    public void crearFormatoA() throws IOException {
        objProcesogrado.setProctitulo(formatoA.getTitulo());
        objProcesogrado.setProcid(new BigDecimal(numeroAleatorio()));
        obtenerUsuario();
        objProcesogrado.setUsuarioList(new ArrayList<Usuario>());
        objProcesogrado.getUsuarioList().add(objUsuario);
        obtenerProductotrabajo("idea de anteproyecto");
        objProcesogrado.setProductodetrabajoList(new ArrayList<Productodetrabajo>());
        objProcesogrado.getProductodetrabajoList().add(objProductotrabajo);
        objProcesogrado.setProcestado(new BigInteger(""+0));
        lstFlujo = ejbFlujo.findAll();
        objFlujo = lstFlujo.get(1);
        objProcesogrado.setFluid(objFlujo);
        ejbProcesogrado.create(objProcesogrado);

        lstParametro = obtenerParametros(objProductotrabajo.getProid().intValue());

        objParametro = obtenerParametro("Titulo");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getTitulo());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("director");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(objUsuario.getUsunombres() + objUsuario.getUsuapellidos());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Tiempo estimado");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getTiempoEstimado());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Recursos");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getRecursos());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("financiacion");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getFuentesFinanciacion());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Objetivos");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getObjetivos());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Aportes");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getContribucion());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("entrega");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getCondicionesEntrega());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("Observaciones");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(formatoA.getObservaciones());
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        objParametro = obtenerParametro("estudiantes");
        objContenido.setConid(new BigDecimal(numeroAleatorio()));
        objContenido.setCondatos(String.valueOf(formatoA.getNumeroEstudiantes()));
        objContenido.setParid(objParametro);
        objContenido.setProcid(objProcesogrado);
        //objContenido.setUsuid(objUsuario);
        ejbContenido.create(objContenido);

        //notificarDepartamento();
        RequestContext.getCurrentInstance().execute("PF('ProyectoCreateDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Registro exitoso"));
    }

    public void notificarDepartamento() {
        lstRol = ejbRol.findAll();
        objRol = new Rol();
        for (Rol r : lstRol) {
            if (r.getRolnombre().equals("Departamento")) {
                objRol = r;
            }
        }

        lstUsuario = objRol.getUsuarioList();

        for (Usuario usu : lstUsuario) {
            notificacion(usu.getUsucorreo(), "Siprogra: Nueva idea de proyecto",
                    "Se ha agregado una nueva idea de proyecto favor revisar SIPROGRA unicauca");
        }
    }
    
    public void seleccionarArchivo(FileUploadEvent event) {
        UploadedFile uf = event.getFile();
        setFile(uf);
        if (uf != null) {
            seleccionar = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccionar", "Su archivo a sido seleccionado con exito"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccionar", "Error al seleccionar Archivo"));
        }
    }

    public void upload() throws IOException {
        String extValidate;
        if (getFile() != null) {
            String ext = getFile().getFileName();
            if (ext != null) {
                extValidate = ext.substring(ext.indexOf(".") + 1);
            } else {
                extValidate = null;
            }

            if (extValidate.equals("pdf")) {
                transferFile(getFile().getFileName(), getFile().getInputstream());
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Exito", "Archivo subido con exito"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Error extencion", "solamente .PDF"));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "seleccione un archivo"));
        }
    }

    public void transferFile(String fileName, InputStream in) throws FileNotFoundException, IOException {
        //OutputStream out = new FileOutputStream(new File(destino + fileName));
        OutputStream out = new FileOutputStream(destino+fileName);
        int reader = 0;
        byte[] bytes = new byte[(int) getFile().getSize()];
        while ((reader = in.read(bytes)) != -1) {
            out.write(bytes, 0, reader);
        }
        in.close();
        out.flush();
        out.close();
    }

    public void notificacion(String email, String asun, String mensaje) {
        this.mailReceptor = email;
        this.asunto = asun;
        this.cuerpo = mensaje;
        this.sendMail();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Notificacion Exitosa", null));

    }

    public Parametro obtenerParametro(String param) {
        for (Parametro paramet : lstParametro) {
            if (paramet.getParnombre().equals(param)) {
                return paramet;
            }
        }
        return null;
    }

    public List<Parametro> obtenerParametros(int id) {
        List<Parametro> lstParametros = ejbParametro.findAll();
        List<Parametro> lstParametrosAc = new ArrayList<>();

        for (Parametro param : lstParametros) {
            int idp = param.getProid().getProid().intValue();
            if (idp == id) {
                lstParametrosAc.add(param);
            }
        }

        return lstParametrosAc;
    }

    public void obtenerProductotrabajo(String actividad) {
        lstProductotrabajos = ejbProductotrabajo.findAll();
        for (Productodetrabajo protra : lstProductotrabajos) {
            if (protra.getPronombre().contains(actividad)) {
                objProductotrabajo = protra;
            }
        }
    }

    public void obtenerUsuario() {
        String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        lstUsuario = ejbUsuario.findAll();
        for (Usuario usr : lstUsuario) {
            if (usr.getUsunombreusuario().equals(login)) {
                objUsuario = usr;
            }
        }
    }

    public boolean isVista() {
        String rol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");
        if (rol.equals("Docente")) {
            return true;
        }
        return false;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    public void agregarEstudiante() 
    {
        lstUsuario = ejbUsuario.findAll();
        boolean flag = false;
        for (Usuario estudiante : lstUsuario) 
        {
            if (cedulaEstudiante == estudiante.getUsucedula().intValue()) 
            {
                objUsuario = estudiante;
                flag = true;
            }
        }
        if (flag) 
        {
            objProcesogrado.getUsuarioList().add(objUsuario);

            ejbProcesogrado.edit(objProcesogrado);
            cedulaEstudiante = 0;
            RequestContext.getCurrentInstance().execute("PF('EstudianteCreateDialog').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Se a creado exitosamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Crear", "la cedula ingresada no pertenece a un estudiante"));
        }
    }

    public void sendMail() {
        Properties props = new Properties();//propiedades a agragar
        props.put("mail.smtp.auth", "true");//autentificacion
        props.put("mail.smtp.starttls.enable", "true");//inicializar el servidor
        props.put("mail.smtp.host", this.servidorSMTP);//host llegada
        props.put("mail.smtp.port", this.puertoEnvio);//puerto de salida

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(miCorreo, miContraseña);
                    }
                });//autentificar el correo

        try {
            Message message = new MimeMessage(session);//se inicia una session
            message.setFrom(new InternetAddress(this.miCorreo));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.mailReceptor));
            message.setSubject(this.asunto);
            message.setText(this.cuerpo);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private int numeroAleatorio() {
        int valorInicial = 100;
        int valorFinal = 99999;
        return (int) (Math.random() * (valorFinal - valorInicial + 1) + valorInicial);
    }
    public List<Procesodegrado> getLstProcesogrado() {
        return ejbProcesogrado.findAll();
    }

    public void setLstProcesogrado(List<Procesodegrado> lstProcesogrado) {
        this.lstProcesogrado = lstProcesogrado;
    }

    public Procesodegrado getObjProcesogrado() {
        return objProcesogrado;
    }

    public void setObjProcesogrado(Procesodegrado objProcesogrado) {
        this.objProcesogrado = objProcesogrado;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public int getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(int cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public FormatoA getFormatoA() {
        return formatoA;
    }

    public void setFormatoA(FormatoA formatoA) {
        this.formatoA = formatoA;
    }

    public RevisionIdea getActaRevisionIdea() {
        return actaRevisionIdea;
    }

    public void setActaRevisionIdea(RevisionIdea actaRevisionIdea) {
        this.actaRevisionIdea = actaRevisionIdea;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isSeleccionar() {
        return seleccionar;
    }

    public void setSeleccionar(boolean seleccionar) {
        this.seleccionar = seleccionar;
    }
    public ActaAprobacionAnteproyecto getActaAnteproyecto() {
        return actaAnteproyecto;
    }

    public void setActaAnteproyecto(ActaAprobacionAnteproyecto actaAnteproyecto) {
        this.actaAnteproyecto = actaAnteproyecto;
    }

    public FormatoG getFormatoG() {
        return formatoG;
    }

    public void setFormatoG(FormatoG formatoG) {
        this.formatoG = formatoG;
    }
    public void prepareAddEstudiante() 
    {
        lstUsuario = ejbUsuario.findAll();
        objUsuario = null;
    }
     public void prepareAsignarEvaluador() 
     {
        if (!objProcesogrado.getUsuarioList().isEmpty()) 
        {
            director = objProcesogrado.getUsuarioList().get(0).getUsunombreusuario() + " " + objProcesogrado.getUsuarioList().get(0).getUsuapellidos();
        }
    }
    public void prepareAddFormatoB() 
    {

    }
    public void prepareRemitirSecretaria() 
    {

    }
    public void asignarEvaluador() 
    {

    }
    public void crearFormatoB() 
    {

    }
    public void editar() {
        ejbProcesogrado.edit(objProcesogrado);
        RequestContext.getCurrentInstance().execute("PF('ProyectoEditDialog').hide()");
        objProcesogrado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Se a actualizado exitosamente"));
    }

    public void borrar() {
        ejbProcesogrado.remove(objProcesogrado);
        objProcesogrado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear", "Se a eliminado exitosamente"));
    }

    public List<Procesodegrado> getLstProyectosDocente() {
        this.obtenerUsuario();
        lstProyectosDocente = objUsuario.getProcesodegradoList();
        return lstProyectosDocente;
    }
    public void setLstProyectosEvaluador(List<Procesodegrado> lstProyectosEvaluador) {
        this.lstProyectosEvaluador = lstProyectosEvaluador;
    }

    public List<Procesodegrado> getLstProyectosJefeDepto() {
        List<Procesodegrado> lstProy = ejbProcesogrado.findAll();
        lstProyectosJefeDepto = new ArrayList<>();
        for (Procesodegrado proy : lstProy) {
            int i = proy.getProductodetrabajoList().size();
            if (i != 0) {
                if (proy.getProductodetrabajoList().get(i - 1).getPronombre().contains("Formato de la de la idea de anteproyecto")) {
                    lstProyectosJefeDepto.add(proy);
                }
            }
        }
        return lstProyectosJefeDepto;
    }

    public void setLstProyectosJefeDepto(List<Procesodegrado> lstProyectosJefeDepto) {
        this.lstProyectosJefeDepto = lstProyectosJefeDepto;
    }

    public List<Procesodegrado> getLstProyectosDepto() {
        return lstProyectosDepto;
    }

    public void setLstProyectosDepto(List<Procesodegrado> lstProyectosDepto) {
        this.lstProyectosDepto = lstProyectosDepto;
    }

    public List<Procesodegrado> getLstProyectosCoordinador() {
        List<Procesodegrado> lstProy = ejbProcesogrado.findAll();
        lstProyectosCoordinador = new ArrayList<>();
        for (Procesodegrado proy : lstProy) {
            int i = proy.getProductodetrabajoList().size();
            if (i != 0) {
                if (proy.getProductodetrabajoList().get(i - 1).getPronombre().contains("Anteproyecto terminado gestionado por el docente")) {
                    lstProyectosCoordinador.add(proy);
                }
            }
        }
        return lstProyectosCoordinador;
    }

    public void setLstProyectosCoordinador(List<Procesodegrado> lstProyectosCoordinador) {
        this.lstProyectosCoordinador = lstProyectosCoordinador;
    }
    public boolean isEvaluado() {
        return evaluado;
    }

    public void setEvaluado(boolean evaluado) {
        this.evaluado = evaluado;
    }

    public List<Usuario> getLstEstudiantePro() {
        if (objProcesogrado != null) {
            List<Usuario> lstUsu = objProcesogrado.getUsuarioList();
            lstEstudiantePro = new ArrayList<>();
            for (Usuario usu : lstUsu) {
                if (usu.getRolList().get(0).getRolnombre().equals("Estudiante")) {
                    lstEstudiantePro.add(usu);
                }
            }
        }
        return lstEstudiantePro;
    }

    public void setLstEstudiantePro(List<Usuario> lstEstudiantePro) {
        this.lstEstudiantePro = lstEstudiantePro;
    }

    public boolean isIdeaAprobada() {
        if (objProcesogrado != null && !objProcesogrado.getProductodetrabajoList().isEmpty()) {
            int i = objProcesogrado.getProductodetrabajoList().size();
            if (i != 0) {
                if (objProcesogrado.getProductodetrabajoList().get(i - 1).getPronombre().contains("resultado de la revision de la idea del proyecto"))
                        //&& objProcesogrado.getEstado().equalsIgnoreCase("aprobado")) 
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void setIdeaAprobada(boolean ideaAprobada) {
        this.ideaAprobada = ideaAprobada;
    }

    public void pru() {
        if (objProcesogrado != null && !objProcesogrado.getProductodetrabajoList().isEmpty()) {
            objProductotrabajo = objProcesogrado.getProductodetrabajoList().get(0);
            lstParametro = objProductotrabajo.getParametroList();
            for (Parametro p : lstParametro) {
                if (p.getParnombre().contains("Tiempo estimado")) {
                    obtenerFormatoA.setTiempoEstimado(p.getPartipo());
                }
            }

        }
    }

    public FormatoA getObtenerFormatoA() {
        if (objProcesogrado != null && !objProcesogrado.getProductodetrabajoList().isEmpty()) {
            objProductotrabajo = objProcesogrado.getProductodetrabajoList().get(0);
            lstParametro = objProductotrabajo.getParametroList();
            for (Parametro p : lstParametro) {
                if (p.getParnombre().contains("Tiempo estimado")) {
                    obtenerFormatoA.setTiempoEstimado(p.getPartipo());
                }
            }

        }
        return obtenerFormatoA;
    }

    public void setObtenerFormatoA(FormatoA obtenerFormatoA) {
        this.obtenerFormatoA = obtenerFormatoA;
    }
    
    public List<String> autocompleteCedula(String query) {
        lstUsuario = ejbUsuario.findAll();
        List<String> lstCedulas = new ArrayList<>();
        for (Usuario estudiante : lstUsuario) {
            if (!estudiante.getRolList().isEmpty()) {
                if (estudiante.getRolList().get(0).getRolnombre().equals("Estudiante")) {
                    String cedula = estudiante.getUsucedula().toString();
                    if (cedula.contains(query)) {
                        lstCedulas.add(cedula);
                    }
                }
            }
        }
        return lstCedulas;
    }
    public String getCarta() 
    {
        return carta;
    }
    public void setCarta(String carta) 
    {
        this.carta = carta;
    }

    public String getAnteproyecto() {
        return anteproyecto;
    }
    public void setAnteproyecto(String anteproyecto) 
    {
        this.anteproyecto = anteproyecto;
    }
    public String getDirector() 
    {
        return director;
    }
    public void setDirector(String director) 
    {
        this.director = director;
    }
}

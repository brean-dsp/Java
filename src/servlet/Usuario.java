package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.Beanportfolio;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {

				Beanportfolio beanportfolio = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("user", beanportfolio);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("download")) {
				Beanportfolio usuario = daoUsuario.consultar(user);
				if(usuario != null) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + usuario.getContentType().split("\\/")[1]);
					
					/*-----Converte a Base64 da imagem do banco para byte[]*/
					byte [] imageFotoBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					
					/*----Coloca os bytes em um objeto de entrada para processar----*/
					InputStream is = new ByteArrayInputStream(imageFotoBytes);
					
					/*----Início da resposta para o navegador----*/
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	
	
	//--------------- Recebe os dados informados no formulario de cadastro de Usúarios ------------//
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			Beanportfolio usuario = new Beanportfolio();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			

			try {

				
				/* Início --------------------- Upload de imagens e PDF -------------------*/
				
				if (ServletFileUpload.isMultipartContent(request)) {
					
					
					Part imagemFoto = request.getPart("foto");
					
					String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));
					
					usuario.setFotoBase64(fotoBase64);
					usuario.setContentType(imagemFoto.getContentType());
					
					//-------------  Modelo que pode ser utilizado para upload de imagem --------------//
					 
					/*List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					for (FileItem fileItem : fileItems) {
						if(fileItem.getFieldName().equals("foto")) {
							
							String fotoBase64 = new Base64().encodeBase64String(fileItem.get());
							String contentType = fileItem.getContentType();
							usuario.setFotoBase64(fotoBase64);
							usuario.setContentType(contentType);
						}
					}*/
					
				}
				
				/* Fim --------------------- Upload de imagens e PDF -------------------*/
				
				String msg = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informada";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "Usuário ja existe com o mesmo login!";
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "\n A senha já existe para outro usuário";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {

					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);
				}

				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Cadastro salvo com sucesso!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	
	// ------------ Converte o fluxo de dados da imagem para um Array de Bytes ------------------- //
	
	private byte[] converteStreamParaByte(InputStream imagem) throws Exception{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		
		
		return baos.toByteArray();
	}

}

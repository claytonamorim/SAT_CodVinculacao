package angulo.sistemas.sat.codvinculacao;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.*;
import java.util.Enumeration;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class GenSig {
	public static String assinar(byte[] bytes, char[] senha){
		byte[] assinado = null;
		String str64 = "";

		try{		
			String str = new String(bytes, "UTF-8");	//"ISO-8859-1"
			String strSenha = new String(senha);
			System.out.println("bytes recebidos:" + str);
			System.out.println("senha recebida:" + strSenha);
			//_________________________________
			String infoProvider = "name = SmartCard library = c:\\windows\\system32\\aetpkss1.dll";
			InputStream confInput = new ByteArrayInputStream(infoProvider.getBytes("UTF-8"));
			Provider p = new sun.security.pkcs11.SunPKCS11(confInput);   
			//_________________________________________________________
			
			Security.addProvider(p); 

			KeyStore ks = KeyStore.getInstance("PKCS11", p);
		
			ks.load(null, senha);    

			PrivateKey priv = null;
			KeyStore.PrivateKeyEntry pkEntry = null;  

			Enumeration<String> aliasesEnum = ks.aliases();  
			while (aliasesEnum.hasMoreElements()) {  
				String alias = aliasesEnum.nextElement();  
				if (ks.isKeyEntry(alias)) {  
					System.out.println("alias:" + alias);
					pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,  
							new KeyStore.PasswordProtection(senha));  
					priv = pkEntry.getPrivateKey();  
					break;  
				}  
			}  

			Signature rsa = Signature.getInstance("SHA256withRSA", p);
			rsa.initSign(priv);
			rsa.update(bytes);
			assinado = rsa.sign();
			
			str64 = Base64.encode(assinado);
		}catch(Exception e){
			System.err.println("Excessão: " + e.toString());
		}

		System.out.println("Outro gerado: " + str64);
		return str64;
	}

}

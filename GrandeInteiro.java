import java.math.BigInteger;


public class GrandeInteiro {	
	
	public static void main(String[] args) {
		long p,q,d;
		BigInteger P,Q,N,Z,E,D,aux;
		BigInteger UM = BigInteger.ONE;
		
		BigInteger normalTexto,criptTexto;
		
		/* 
		 *  Gera numeros do tipo long aleatoriamente para testar se são primos ou não.
		 */
		p = (long)(Math.random()*10000L);		
		q = (long)(Math.random()*10000L);		
		d = (long)(Math.random()*1000L);
		
		/* 
		 * Testa se os numeros gerados randomicamante são ou não primos
		 * em caso negativo incrementa 1 e tenta novamente.
		 */
		
		while(testaPrimo(p) == false){
			p++;
		}				
		while(testaPrimo(q) == false){
			q++;
		}		
		
		while(testaPrimo(d) == false){
			d++;
		}
		
		/*
		 *  -- Transforma os numeros do tipo long para o tipo BigInteger. Com a garantia que 
		 *  -- são numeros primos.
		 */
		P = BigInteger.valueOf(p);
		Q = BigInteger.valueOf(q);		
		
		
		/*
		 * Determinar Numero N da criptografia
		 */		
		N = P.multiply(Q); // isso é igual a "n = p * q"
		System.out.println(N + " este é o N");
		
		/*
		 * Determinar numero Z -> z = (p - 1) * (q - 1)
		 */
		
		Z = (P.subtract(UM).multiply(Q.subtract(UM)));
		
		
		/*
		 * Temos que ter um Numero D que seja co-Primo em relação a Z
		 * Regra dos co-Primos se o maior divisor comum (mdc) entre dois
		 * numeros for o número 1, estes serão co-Primos entre si.
		 * Como D é um numero primo o unico divisor que tera em comum com 
		 * Z é o numero UM.
		 * 
		 */
		D = BigInteger.valueOf(d);
		System.out.println(D + " este é o D");
		
		/*
		 * Numero E é determinado seguindo a seguinte regra: (E * D) mod Z = 1
		 */
		E = BigInteger.valueOf(2);
		aux = E.multiply(D).mod(Z);
		
		while(numE(aux) == false){
			E = E.add(UM);
			aux = E.multiply(D).mod(Z);						
		}
		System.out.println(E+ " Este é o E");
		
		
		/*
		 * 				Criptografia e Descriptografia
		 */
			/*
			 * 					Criptografando
			 *  texto criptografado = (texto original elevado a E) mod n
			 */
		
		
		normalTexto = BigInteger.valueOf(35); // -> teste para criptografia
		System.out.println(normalTexto+ " teste ");
		
		criptTexto = normalTexto.modPow(E, N);
		System.out.println(criptTexto + "  Texto criptografado");
		
		/*
		 * 					Descriptografando
		 * 	texto Original = (Texto Criptografado elevado a D) mod N
		 */
				
		normalTexto = criptTexto.modPow(D, N);
		System.out.println(normalTexto+ " Tem que estar igual");		
			
	}
	
	/*
	 *								 Métodos de verificação
	 */
	
	public static boolean numE(BigInteger aux1){
		//BigInteger UMA = BigInteger.ONE;
		if(aux1.longValue() != 1){
			return false;
		}
		return true;
	}
	
	/*
	 * Método para verificar se o numero é primo. Usando a regra do Crivo de Eratóstenes,
	 * Um numero é primo se não for divisivel por nenhum número a contar do 2
	 * até a raiz quadrada dele mesmo (arrendodando para baixo). Caso retorne 
	 * zero em alguma operação o número não é primo.
	 */
	
	public static boolean testaPrimo(long testaNum){
		for(int i = 2; i < Math.sqrt(testaNum); i++){
			if(testaNum % i == 0){
				return false;
			}
		}
		return true;
		
		
	}
	

}


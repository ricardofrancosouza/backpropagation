package mod;

public class Simplenet {
	public double m_fPesos[][] = new double[3][3];
	public static void main(String args[]){
		Simplenet sn = new Simplenet();
		sn.iniciaMatriz();
		sn.SaidaMatrizPesos();
	}
		public void SaidaMatrizPesos(){
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					System.out.print(m_fPesos[i][j] + "  ");
				}
				System.out.println();
			}
		}
		public void iniciaMatriz(){
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					// Número aleatório entre -3 and 3.
					m_fPesos[i][j] = ((Math.random())/(32767/6) - 3);
				}
			}	
		}
		//Método executar/rodar
		public double rodar(double i1,double i2,double d){
			// Essas são as principais variáveis usadas na  
			// rotina. 
			double net1, net2, i3, i4;
			// Calcular o calor dos net para as camadas escondidas dos neurônios
			net1 = 1 * m_fPesos[0][0] + i1 * m_fPesos[1][0] +
				  i2 * m_fPesos[2][0];
			net2 = 1 * m_fPesos[0][1] + i1 * m_fPesos[1][1] +
				  i2 * m_fPesos[2][1];
			// Use the hardlimiter function - the Sigmoid.
			i3 = sigmoid(net1);
			i4 = sigmoid(net2);
			// Now, calculate the net for the final output layer.
			net1 = 1 * m_fPesos[0][2] + i3 * m_fPesos[1][2] +
			   	  i4 * m_fPesos[2][2];
			//System.out.println("Net > "+net1);
			return sigmoid(net1);
		}
		//Treinamento
		public double treinamento(double i1, double i2, double d){
			// Função de treinamento retorna o erro
			double var = (double) (Math.abs(rodar(i1,i2,d) - d));
			//System.out.println("Erro BackPropagation > " + var);
			return var;
		}
		public double sigmoid(double num) {
			return (double)(1/(1+Math.exp(-num)));
		}
		public void alterarPesos(double montante){
			SaidaMatrizPesos();
			//System.out.println("Saída após modificação dos Pesos");
			//System.out.println("Montante > "+ montante);
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					m_fPesos[i][j] += (double)(Math.random())/(32767/(montante * 2)) - montante;
				}
				System.out.println("");
			}
			System.out.println("");
		}
		//Pega Pesos
		public void pegaPesos(double vetorPesos[]){
			int z = 0;
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					vetorPesos[z] = m_fPesos[i][j];
					z++;
				}
			}			
		}
		public void configPesos(double vetorPesos[]) {
			int z = 0;
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					m_fPesos[i][j] = vetorPesos[z];
					z++;
				}
			}
	}
}

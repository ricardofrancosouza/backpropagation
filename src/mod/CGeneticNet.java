package mod;

public class CGeneticNet {
	 private int CGN_POPULATION = 150;
	    private double CGN_THRESHOLD = 0.001;
	    private int CGN_MAXITER = 1000;
	    private Simplenet[] m_pcPopulation;
	    private double m_fErrors[];
	    public CGeneticNet(){
	        m_pcPopulation = new Simplenet[CGN_POPULATION];
	        m_fErrors = new double[CGN_POPULATION];
	        for(int i=0;i < CGN_POPULATION; i++){
	            // Weights automatically initialized 
	            // in neural network constructor.
	            m_pcPopulation[i] = new Simplenet();
	        }
	    }
	    public Simplenet Run(){
	        double error = 0;
	        for(int iter=0;iter < CGN_MAXITER; iter++){
	            for (int i=0;i<CGN_POPULATION;i++) {
	                error = ((m_pcPopulation[i].treinamento(0,0,0) + m_pcPopulation[i].treinamento(0,1,1) + m_pcPopulation[i].treinamento(1,0,1) + m_pcPopulation[i].treinamento(1,1,0)) / 4);
	                System.out.println("Erro AG = " + error);
	                if(error < CGN_THRESHOLD)
	                {
	                    System.out.println("Venci");
	                    return m_pcPopulation[i];
	                }
	                m_fErrors[i] = error;
	            }
	            SortFitnesses();
	            NewPopulation();
		}
		return null;
	    }
	    void SortFitnesses(){
	        int n = CGN_POPULATION;
		int disorder = n;
		while(disorder != 0){
				disorder = 0;
				for(int i = 1; i < n; i++){
					if(m_fErrors[i] < m_fErrors[i-1]){
	                    double m_fTemp = m_fErrors[i-1];
	                    m_fErrors[i-1] = m_fErrors[i];
	                    m_fErrors[i] = m_fTemp;
	                    Simplenet tempnn = m_pcPopulation[i-1];
	                    m_pcPopulation[i-1] = m_pcPopulation[i];
	                    m_pcPopulation[i] = tempnn;
	                    disorder++;
	                }
	            }
	            n--;
		}
	    }
	    void NewPopulation(){
		int id1, id2;
		double wp1[] = new double[9];
		double wp2[] = new double[9];
		for (int i = 0; i < CGN_POPULATION/2; i++){
	            id1 = (int) (Math.random() % CGN_POPULATION / 2);
	            id2 = (int) (Math.random() % CGN_POPULATION / 2 + CGN_POPULATION / 2);
	            m_pcPopulation[id1].pegaPesos(wp1);
	            m_pcPopulation[id2].pegaPesos(wp2);
	            for (int j = 0; j < 2; j++){
	                double temp;
	                temp = wp1[j+6];
	                wp1[j+6] = wp2[j+6];
	                wp2[j+6] = temp;
	            }
	            if(Math.random()%10 < 2){
	                for (int k = 0; k < 3; k++){
	                    for (int j=0;j<3;j++) {
	                            m_pcPopulation[id1].alterarPesos((double)(Math.random())/(32767/2) - 1);
	                            m_pcPopulation[id2].alterarPesos((double)(Math.random())/(32767/2) - 1);
	                    }
	                }
	            }
	            m_pcPopulation[id2].configPesos(wp1);
	        }
	    }
}

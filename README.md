# GenMap
---------
## 1. Objetivo

O objetivo deste projeto é gerar pequenos relatórios com base em pontos específicos de arquivos, por exemplo, GenBank.

## 2. Funcionalidades

- Mapeamento de éxons e íntrons;
- Cálculo de tamanho da área CDS;

### 2.1. Mapeamento de éxons e íntrons

Arquivos no formato GenBank (.gb) possuem uma área de _features_. 

Nesta área será possível encontrar trechos parecidos com o do exemplo abaixo:

     mRNA            join(1..123,883..1700,4520..4715,5686..5853,5965..6111,
                     6261..6340,7447..7629,7733..7884,8058..8184,9110..9346,
                     10569..10689,10895..10981,11285..11392,11894..12054,
                     12781..12834,12943..13060,13536..13671,13784..14030,
                     15446..15568,15697..15865,16047..16255,17260..17420,
                     17969..18491)
                     /gene="POLG"
                     /gene_synonym="MDP1; MIRAS; MTDPS4A; MTDPS4B; PEO; POLG1;
                     POLGA; SANDO; SCAE"
                     /product="polymerase (DNA directed), gamma, transcript
                     variant 1"
                     /note="Derived by automated computational analysis using
                     gene prediction method: BestRefSeq."
                     /transcript_id="NM_002693.2"
                     /db_xref="GI:187171275"
                     /db_xref="GeneID:5428"
                     /db_xref="HGNC:HGNC:9179"
                     /db_xref="HPRD:01438"
                     /db_xref="MIM:174763"

Se a área onde está o join for copiada e higienizada (retirar as quebras de linha e espaços) e passada como parâmetro para o aplicativo, será gerado um relatório na tela conforme o exemplo abaixo:

	--- Exons ---
	1:	1..123 = 122
	2:	883..1700 = 817
	3:	4520..4715 = 195
	4:	5686..5853 = 167
	5:	5965..6111 = 146
	6:	6261..6340 = 79
	7:	7447..7629 = 182
	8:	7733..7884 = 151
	9:	8058..8184 = 126
	10:	9110..9346 = 236
	11:	10569..10689 = 120
	12:	10895..10981 = 86
	13:	11285..11392 = 107
	14:	11894..12054 = 160
	15:	12781..12834 = 53
	16:	12943..13060 = 117
	17:	13536..13671 = 135
	18:	13784..14030 = 246
	19:	15446..15568 = 122
	20:	15697..15865 = 168
	21:	16047..16255 = 208
	22:	17260..17420 = 160
	23:	17969..18491 = 522

	--- Introns ---
	1:	124..882 = 758
	2:	1701..4519 = 2818
	3:	4716..5685 = 969
	4:	5854..5964 = 110
	5:	6112..6260 = 148
	6:	6341..7446 = 1105
	7:	7630..7732 = 102
	8:	7885..8057 = 172
	9:	8185..9109 = 924
	10:	9347..10568 = 1221
	11:	10690..10894 = 204
	12:	10982..11284 = 302
	13:	11393..11893 = 500
	14:	12055..12780 = 725
	15:	12835..12942 = 107
	16:	13061..13535 = 474
	17:	13672..13783 = 111
	18:	14031..15445 = 1414
	19:	15569..15696 = 127
	20:	15866..16046 = 180
	21:	16256..17259 = 1003
	22:	17421..17968 = 547

Onde:

O primeiro número é o número do éxon ou do íntron, os números que seguem são as posições de início e fim (os éxons são informados pelo usuário e os íntrons são calculados com base nas lacunas que aparecem entre os pontos) e o último número é a quantidade de bases que existe para este éxon ou íntron.

### 2.2. Cálculo de tamanho da área CDS

A área de _features_ do arquivo GenBank possui a sessão CDS, conforme exemplo abaixo:

     CDS             join(1042..1700,4520..4715,5686..5853,5965..6111,
                     6261..6340,7447..7629,7733..7884,8058..8184,9110..9346,
                     10569..10689,10895..10981,11285..11392,11894..12054,
                     12781..12834,12943..13060,13536..13671,13784..14030,
                     15446..15568,15697..15865,16047..16255,17260..17420,
                     17969..18045)
                     /gene="POLG"
                     /gene_synonym="MDP1; MIRAS; MTDPS4A; MTDPS4B; PEO; POLG1;
                     POLGA; SANDO; SCAE"
                     /note="Derived by automated computational analysis using
                     gene prediction method: BestRefSeq."
                     /codon_start=1
                     /product="DNA polymerase subunit gamma-1"
                     /protein_id="NP_001119603.1"
                     /db_xref="GI:187171277"
                     /db_xref="CCDS:CCDS10350.1"
                     /db_xref="GeneID:5428"
                     /db_xref="HGNC:HGNC:9179"
                     /db_xref="HPRD:01438"
                     /db_xref="MIM:174763"
                     /translation="MSRLLWRKVAGATVGPGPVPAPGRWVSSSVPASDPSDGQRRRQQ
                     QQQQQQQQQQQPQQPQVLSSEGGQLRHNPLDIQMLSRGLHEQIFGQGGEMPGEAAVRR
                     SVEHLQKHGLWGQPAVPLPDVELRLPPLYGDNLDQHFRLLAQKQSLPYLEAANLLLQA
                     QLPPKPPAWAWAEGWTRYGPEGEAVPVAIPEERALVFDVEVCLAEGTCPTLAVAISPS
                     AWYSWCSQRLVEERYSWTSQLSPADLIPLEVPTGASSPTQRDWQEQLVVGHNVSFDRA
                     HIREQYLIQGSRMRFLDTMSMHMAISGLSSFQRSLWIAAKQGKHKVQPPTKQGQKSQR
                     KARRGPAISSWDWLDISSVNSLAEVHRLYVGGPPLEKEPRELFVKGTMKDIRENFQDL
                     MQYCAQDVWATHEVFQQQLPLFLERCPHPVTLAGMLEMGVSYLPVNQNWERYLAEAQG
                     TYEELQREMKKSLMDLANDACQLLSGERYKEDPWLWDLEWDLQEFKQKKAKKVKKEPA
                     TASKLPIEGAGAPGDPMDQEDLGPCSEEEEFQQDVMARACLQKLKGTTELLPKRPQHL
                     PGHPGWYRKLCPRLDDPAWTPGPSLLSLQMRVTPKLMALTWDGFPLHYSERHGWGYLV
                     PGRRDNLAKLPTGTTLESAGVVCPYRAIESLYRKHCLEQGKQQLMPQEAGLAEEFLLT
                     DNSAIWQTVEELDYLEVEAEAKMENLRAAVPGQPLALTARGGPKDTQPSYHHGNGPYN
                     DVDIPGCWFFKLPHKDGNSCNVGSPFAKDFLPKMEDGTLQAGPGGASGPRALEINKMI
                     SFWRNAHKRISSQMVVWLPRSALPRAVIRHPDYDEEGLYGAILPQVVTAGTITRRAVE
                     PTWLTASNARPDRVGSELKAMVQAPPGYTLVGADVDSQELWIAAVLGDAHFAGMHGCT
                     AFGWMTLQGRKSRGTDLHSKTATTVGISREHAKIFNYGRIYGAGQPFAERLLMQFNHR
                     LTQQEAAEKAQQMYAATKGLRWYRLSDEGEWLVRELNLPVDRTEGGWISLQDLRKVQR
                     ETARKSQWKKWEVVAERAWKGGTESEMFNKLESIATSDIPRTPVLGCCISRALEPSAV
                     QEEFMTSRVNWVVQSSAVDYLHLMLVAMKWLFEEFAIDGRFCISIHDEVRYLVREEDR
                     YRAALALQITNLLTRCMFAYKLGLNDLPQSVAFFSAVDIDRCLRKEVTMDCKTPSNPT
                     GMERRYGIPQGEALDIYQIIELTKGSLEKRSQPGP"

O primeiro ponto desta área é formado pelas posições que a compõe, no caso:

	join(1042..1700,4520..4715,5686..5853,5965..6111,6261..6340,7447..7629,7733..7884,8058..8184,9110..9346,10569..10689,10895..10981,11285..11392,11894..12054,12781..12834,12943..13060,13536..13671,13784..14030,15446..15568,15697..15865,16047..16255,17260..17420,17969..18045)
	
Portanto, se esta parte for extraída e passada como parâmetro, o programa terá condições de informar a seguinte saída:

	--- CDS areas ---
	1:	1042..1700 = 658
	2:	4520..4715 = 195
	3:	5686..5853 = 167
	4:	5965..6111 = 146
	5:	6261..6340 = 79
	6:	7447..7629 = 182
	7:	7733..7884 = 151
	8:	8058..8184 = 126
	9:	9110..9346 = 236
	10:	10569..10689 = 120
	11:	10895..10981 = 86
	12:	11285..11392 = 107
	13:	11894..12054 = 160
	14:	12781..12834 = 53
	15:	12943..13060 = 117
	16:	13536..13671 = 135
	17:	13784..14030 = 246
	18:	15446..15568 = 122
	19:	15697..15865 = 168
	20:	16047..16255 = 208
	21:	17260..17420 = 160
	22:	17969..18045 = 76


	Total Size = 3698


## 3. Utilização

### 3.1. Parâmetros

	mapgbjoin="join(1..123,800..1000)"
	
O texto após o **=** é informado pelo usuário e deverá ser extraído do arquivo GenBank.

	cds-size="join(1..123,800..1000)"
	
O texto após o **=** é informado pelo usuário e deverá ser extraído do arquivo GenBank.

	--help
	
Exibe a lista de parâmetros disponíveis do aplicativo e como utilizá-los.


### 3.2. Requisitos

Se você é apenas usuário, basta ter o Java 1.6+ instalado em sua máquina. 

Recomendamos a versão 1.8+.

Desenvolvedores devem possuir o Java (JDK) seguindo o mesmo padrão para versões.

É necessário também que o desenvolvedor possua o Maven 3.2+ instalado.

### 3.3. Download do fonte e empacotamento

Para baixar o código fonte para sua máquina, basta executar o comando:

	$ git clone https://bitbucket.org/unesp/genmap.git
	
Para gerar o jar que será distribuído execute o seguinte comando na pasta raiz do projeto:

	$ mvn clean install
	
Feito isso, o jar gerado estará dentro da pasta **target**. Este é o executável que deve ser distribuído.

### 3.4. Download do executável

Acesse a área de download com todas versões geradas [clicando aqui](https://bitbucket.org/unesp/genmap/downloads).

### 3.5. Execução

No terminal do seu computador execute o comando:

	$ java -jar genmap-[versao].jar mapgbjoin="join(1..123,800..1000)"
	

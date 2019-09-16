package indexation.processing;

/**
 * Objet racinisant les tokens à l'aide des règles
 * définies par Porter. Cette classe inclut du code
 * source développé par Porter en domaine public,
 * et disponible à l'adresse suivante :
 * <a href="https://tartarus.org/martin/PorterStemmer/java.txt">Porter Stemmer Java code</a>
 */
public class PorterStemmer extends AbstractStemmer
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	@Override
	public String stemType(String string)
	{	char[] charArray = string.toCharArray();
		add(charArray, charArray.length);
		stem();
		String result = getResultBufferAsString();
		return result;
	}
	
	////////////////////////////////////////////////////
	//	CODE OFFICIEL DE PORTER
	////////////////////////////////////////////////////
	/** */
	private char[] buff = new char[INC];;
	/** offset into b */
	private int i = 0;
	/** offset to end of stemmed word */
	private int iEnd = 0;
	/** */
	private int j;
	/** */
	private int k;
	/** unit of size whereby b is increased */
	private static final int INC = 50;
	
	/**
	 * Add a character to the word being stemmed.  When you are finished
	 * adding characters, you can call stem(void) to stem the word.
	 * 
	 * @param ch
	 * 		... 
	 */
	public void add(char ch)
	{	if (i == buff.length)
		{	char[] newBuff = new char[i+INC];
			for (int c = 0; c < i; c++) 
				newBuff[c] = buff[c];
				buff = newBuff;
		}
		buff[i++] = ch;
	}

	/** Adds wLen characters to the word being stemmed contained in a portion
	 * of a char[] array. This is like repeated calls of add(char ch), but
	 * faster.
	 * 
	 * @param w
	 * 		... 
	 * @param wLen
	 * 		... 
	 */
	public void add(char[] w, int wLen)
	{	if (i+wLen >= buff.length)
		{	char[] newBuff = new char[i+wLen+INC];
			for (int c = 0; c < i; c++) 
				newBuff[c] = buff[c];
			buff = newBuff;
		}
		for (int c = 0; c < wLen; c++) 
			buff[i++] = w[c];
	}

	/**
	 * After a word has been stemmed, it can be retrieved by getResultBufferAsString(),
	 * or a reference to the internal buffer can be retrieved by getResultBuffer
	 * and getResultLength (which is generally more efficient.)
	 * 
	 * @return
	 * 		... 
	 */
	public String getResultBufferAsString() 
	{	return new String(buff,0,iEnd); 
	}

	/**
	 * Returns the length of the word resulting from the stemming process.
	 * 
	 * @return
	 * 		... 
 	*/
	public int getResultLength()
	{	return iEnd; 
	}

	/**
	 * Returns a reference to a character buffer containing the results of
	 * the stemming process.  You also need to consult getResultLength()
	 * to determine the length of the result.
	 * 
	 * @return
	 * 		... 
	 */
	public char[] getResultBuffer()
	{	return buff; 
	}

	/** 
	 * cons(i) is true <=> b[i] is a consonant. 
	 * 
	 * @param i
	 * 		...
	 * @return
	 * 		... 
	 */
	private boolean cons(int i)
	{	switch (buff[i])
		{	case 'a': 
			case 'e': 
			case 'i': 
			case 'o': 
			case 'u': 
				return false;
			case 'y': 
				return (i==0) ? true : !cons(i-1);
			default: 
				return true;
		}
	}

	/** 
	 * m() measures the number of consonant sequences between 0 and j. if c is
	 * a consonant sequence and v a vowel sequence, and <..> indicates arbitrary
	 * presence,
	 * 
	 * 	<c><v>       gives 0
	 * 	<c>vc<v>     gives 1
	 * 	<c>vcvc<v>   gives 2
	 * 	<c>vcvcvc<v> gives 3
	 * 	....
	 * 
	 * @return
	 * 		... 
	 */
	private int m()
	{	int n = 0;
		int i = 0;
		while(true)
		{	if (i > j) return n;
				if (! cons(i)) 
					break; 
				i++;
		}
		i++;
		while(true)
		{	while(true)
			{	if (i > j) return n;
					if (cons(i)) 
						break;
					i++;
			}
			i++;
			n++;
			while(true)
			{	if (i > j) return n;
					if (! cons(i)) 
						break;
					i++;
			}
			i++;
		}
	}

	/** 
	 * vowelinstem() is true <=> 0,...j contains a vowel 
	 * 
	 * @return
	 * 		... 
	 */
	private boolean vowelinstem()
	{	int i; 
		for (i = 0; i <= j; i++) 
			if (! cons(i)) 
				return true;
		return false;
	}

	/** 
	 * doublec(j) is true <=> j,(j-1) contain a double consonant. 
	 * 
	 * @param j
	 * 		...
	 * @return
	 * 		... 
	 */
	private boolean doublec(int j)
	{	if (j < 1) 
			return false;
		if (buff[j] != buff[j-1]) 
			return false;
		return cons(j);
	}

	/** 
	 * cvc(i) is true <=> i-2,i-1,i has the form consonant - vowel - consonant
	 * and also if the second c is not w,x or y. this is used when trying to
	 * restore an e at the end of a short word. e.g.
	 * 
	 * cav(e), lov(e), hop(e), crim(e), but
	 * snow, box, tray.
	 * 
	 * @param i
	 * 		...
	 * @return
	 * 		... 
	 */
	private boolean cvc(int i)
	{	if (i < 2 || !cons(i) || cons(i-1) || !cons(i-2)) 
			return false;
		{	int ch = buff[i];
			if (ch == 'w' || ch == 'x' || ch == 'y') 
				return false;
		}
		return true;
	}
	
	/**
	 * ...
	 * 
	 * @param s
	 * 		...
	 * @return
	 * 		...
	 */
	private boolean ends(String s)
	{	int l = s.length();
		int o = k-l+1;
		if (o < 0) 
			return false;
		for (int i = 0; i < l; i++) 
			if (buff[o+i] != s.charAt(i)) 
				return false;
		j = k-l;
		return true;
	}

	/** 
	 * setto(s) sets (j+1),...k to the characters in the string s, 
	 * readjusting k. 
	 * 
	 * @param s
	 * 		... 
	 */
	private void setto(String s)
	{	int l = s.length();
		int o = j+1;
		for (int i = 0; i < l; i++) 
			buff[o+i] = s.charAt(i);
		k = j+l;
	}

	/** 
	 * r(s) is used further down. 
	 * 
	 * @param s
	 * 		... 
	 */
	private void r(String s) 
	{	if (m() > 0) 
			setto(s); 
	}

	/** 
	 * step1() gets rid of plurals and -ed or -ing. e.g.
	 *
	 *        caresses  ->  caress
	 *        ponies    ->  poni
	 *        ties      ->  ti
	 *        caress    ->  caress
	 *        cats      ->  cat
	 *
	 *        feed      ->  feed
	 *        agreed    ->  agree
	 *        disabled  ->  disable
	 *
	 *        matting   ->  mat
	 *        mating    ->  mate
	 *        meeting   ->  meet
	 *        milling   ->  mill
	 *        messing   ->  mess
	 *
	 *        meetings  ->  meet
	 */
	private void step1()
	{	if (buff[k] == 's')
		{	if (ends("sses")) 
				k -= 2; 
			else if (ends("ies")) 
				setto("i"); 
			else if (buff[k-1] != 's') 
				k--;
		}
		if (ends("eed")) 
		{	if (m() > 0) 
				k--; 
		} 
		else if ((ends("ed") || ends("ing")) && vowelinstem())
		{	k = j;
			if (ends("at")) 
				setto("ate");
			else if (ends("bl")) 
				setto("ble"); 
			else if (ends("iz")) 
				setto("ize"); 
			else if (doublec(k))
			{	k--;
				{	int ch = buff[k];
					if (ch == 'l' || ch == 's' || ch == 'z') 
						k++;
				}
			}
			else if (m() == 1 && cvc(k)) 
				setto("e");
		}
	}

	/** 
	 * step2() turns terminal y to i when there is another vowel in the stem. 
	 */
	private void step2() 
	{	if (ends("y") && vowelinstem()) 
			buff[k] = 'i'; 
	}

	/** 
	 * step3() maps double suffices to single ones. so -ization ( = -ize plus
	 * -ation) maps to -ize etc. note that the string before the suffix must give
	 * m() > 0. 
	 */
	private void step3() 
	{	/* For Bug 1 */ 
		if (k == 0) 
			return; 
		switch (buff[k-1])
		{	case 'a': 
				if (ends("ational")) 
				{	r("ate"); 
					break; 
				}
				if (ends("tional"))
				{	r("tion"); 
					break; 
				}
				break;
			case 'c': 
				if (ends("enci")) 
				{	r("ence"); 
					break; 
				}
	            if (ends("anci")) 
	            {	r("ance"); 
	            	break; 
            	}
	            break;
			case 'e': 
				if (ends("izer")) 
				{	r("ize"); 
					break; 
				}
				break;
			case 'l': 
				if (ends("bli")) 
				{	r("ble"); 
					break; 
				}
				if (ends("alli")) 
				{	r("al"); 
					break; 
				}
				if (ends("entli")) 
				{	r("ent"); 
					break; 
				}
				if (ends("eli")) 
				{	r("e"); 
					break; 
				}
				if (ends("ousli")) 
				{	r("ous"); 
					break; 
				}
				break;
			case 'o': 
				if (ends("ization")) 
				{	r("ize"); 
					break; 
				}
				if (ends("ation")) 
				{	r("ate"); 
					break; 
				}
				if (ends("ator")) 
				{	r("ate"); 
					break; 
				}
				break;
			case 's': 
				if (ends("alism")) 
				{	r("al"); 
					break; 
				}
				if (ends("iveness")) 
				{	r("ive"); 
					break; 
				}
				if (ends("fulness")) 
				{	r("ful"); 
					break; 
				}
				if (ends("ousness")) 
				{	r("ous"); 
					break; 
				}
				break;
			case 't': 
				if (ends("aliti")) 
				{	r("al"); 
					break; 
				}
				if (ends("iviti")) 
				{	r("ive"); 
					break; 
				}
				if (ends("biliti")) 
				{	r("ble"); 
					break; 
				}
				break;
			case 'g': 
				if (ends("logi")) 
				{	r("log"); 
					break; 
				}
		} 
	}

	/** 
	 * step4() deals with -ic-, -full, -ness etc. similar strategy to step3. 
	 */
	private void step4() 
	{	switch (buff[k])
		{	case 'e': 
			if (ends("icate")) 
			{	r("ic"); 
				break; 
			}
			if (ends("ative")) 
			{	r(""); 
				break; 
			}
			if (ends("alize")) 
			{	r("al"); 
				break; 
			}
			break;
			case 'i': 
				if (ends("iciti")) 
				{	r("ic"); 
					break; 
				}
				break;
			case 'l': 
				if (ends("ical"))
				{	r("ic"); 
					break; 
				}
				if (ends("ful")) 
				{	r(""); 
					break; 
				}
				break;
			case 's': 
				if (ends("ness")) 
				{	r(""); 
					break; 
				}
			break;
		} 
	}

	/** 
	 * step5() takes off -ant, -ence etc., in context <c>vcvc<v>. 
	 */
	private void step5()
	{	/* for Bug 1 */ 
		if (k == 0) 
			return; 
		switch (buff[k-1])
		{	case 'a': 
			if (ends("al")) 
				break; 
			return;
			case 'c': 
				if (ends("ance")) 
					break;
				if (ends("ence")) 
					break; 
				return;
			case 'e': 
				if (ends("er")) 
					break; 
				return;
			case 'i': 
				if (ends("ic")) 
					break; 
				return;
			case 'l': 
				if (ends("able")) 
					break;
				if (ends("ible")) 
					break; 
				return;
			case 'n': 
				if (ends("ant")) 
					break;
				if (ends("ement")) 
					break;
				if (ends("ment")) 
					break;
				/* element etc. not stripped before the m */
				if (ends("ent")) 
					break; 
				return;
			case 'o': 
				/* j >= 0 fixes Bug 2 */
				if (ends("ion") && j >= 0 && (buff[j] == 's' || buff[j] == 't')) 
					break;
                if (ends("ou")) 
					break; 
				/* takes care of -ous */
				return;
			case 's': 
				if (ends("ism")) 
					break; 
				return;
			case 't': 
				if (ends("ate")) 
					break;
				if (ends("iti")) 
					break; 
				return;
			case 'u': 
				if (ends("ous")) 
					break; 
				return;
			case 'v': 
				if (ends("ive")) 
					break; 
				return;
			case 'z': 
				if (ends("ize")) 
					break; 
				return;
			default: 
				return;
		}
		if (m() > 1) 
			k = j;
	}

	/** 
	 * step6() removes a final -e if m() > 1. 
	 */
	private void step6()
	{	j = k;
		if (buff[k] == 'e')
		{	int a = m();
			if (a > 1 || a == 1 && !cvc(k-1)) 
				k--;
		}
		if (buff[k] == 'l' && doublec(k) && m() > 1) 
			k--;
	}

	/** 
	 * Stem the word placed into the Stemmer buffer through calls to add().
	 * Returns {@code true} if the stemming process resulted in a word different
	 * from the input. You can retrieve the result with
	 * {@link #getResultLength()}/{@link #getResultBuffer()} or {@link #getResultBufferAsString()}.
	 */
	private void stem()
	{	k = i - 1;
		if (k > 1) 
		{	step1(); 
			step2(); 
			step3(); 
			step4(); 
			step5(); 
			step6(); 
		}
		iEnd = k+1;
		i = 0;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	/**
	 * Test des méthodes de cette classe.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws Exception 
	 * 		Problème quelconque rencontré.
	 */
	public static void main(String[] args) throws Exception 
	{	// test de stemType
		PorterStemmer stemmer = new PorterStemmer();
		String types[] = new String[] {"caresses","ponies","caress","cats", "feed", 
				"agreed", "plastered", "bled", "motoring", "sing", "happy", "sky", 
				"relational", "conditional", "rational", "valenci", "hesitansi", 
				"triplicate", "formative", "formalize", "electriciti", "revival",
				"allowance", "inference", "airliner", "probate", "rate", "cease", 
				"controll", "roll", "generalizations", "oscillators", "steps", "cat"};
		for(String type: types)
			System.out.println(type+" >> "+stemmer.stemType(type));
	}
}

import java.util.*;
import java.math.BigInteger;
import java.io.*;

class Codechef{
	
	static class MyScanner{
		BufferedReader br;
		StringTokenizer st;
		
		MyScanner(FileReader fileReader){
			br = new BufferedReader(fileReader);
		}
		
		MyScanner(){
			br = new BufferedReader(new InputStreamReader(System.in));
		}
    
    String nn(){
			while(st == null || !st.hasMoreElements()){
				try{
					st = new StringTokenizer(br.readLine());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		char nc(){
			return nn().charAt(0);
		}
    
    int ni(){
			return Integer.parseInt(nn());
		}
		
		long nl(){
			return Long.parseLong(nn());
		}
		
		double nd(){
			return Double.parseDouble(nn());
		}
		
		int[] niArr0(int n){
			int[] ar = new int[n];
			for(int i = 0; i < n; i++) ar[i] = ni();
			return ar;
		}
		
		int[] niArr1(int n){
			int[] ar = new int[n + 1];
			for(int i = 1; i <= n; i++) ar[i] = ni();
			return ar;
		}
		
		long[] nlArr0(int n){
			long[] ar = new long[n];
			for(int i = 0; i < n; i++) ar[i] = nl();
			return ar;
		}
    long[] nlArr1(int n){
			long[] ar = new long[n + 1];
			for(int i = 1; i <= n; i++) ar[i] = nl();
			return ar;
		}
	}
	
	private static PrintWriter out;
	
	public static <T> void mprintln(T ... ar){
		for(T i: ar) out.print(i + " ");
		out.println();
	}
	
	static int count = 0;
	
    public static void main(String[] args){
		
		MyScanner sc = new MyScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		// out = new PrintWriter(new PrintStream(new File("C:\\Users\\Naveen\\Desktop\\array.txt")));
		
		getAns(sc);
		
		out.close();
    }
    
    private static void getAns(MyScanner sc){
		int t = sc.ni();
		while(t-- > 0){
			char[][] ar = new char[5][2];
			for(int i = 0; i < 5; i++) ar[i] = sc.nn().toCharArray();
			out.println(findAns(ar));
		}
	}
	
	private static String findAns(char[][] ar){
		int[] count = new int[20];
		int spades = 0, hearts = 0, clubs = 0, diamonds = 0;
		for(char[] cur: ar){
			switch(cur[0]){
				case 'T': count[10]++; break;
				case 'J': count[11]++; break;
				case 'Q': count[12]++; break;
				case 'K': count[13]++; break;
				case 'A': count[14]++; count[1]++; break;
				default: count[cur[0] - '0']++;
			}
			
			switch(cur[1]){
				case 'S': spades++; break;
				case 'H': hearts++; break;
				case 'D': diamonds++; break;
				case 'C': clubs++; break;
			}
		}
		
		// System.out.println(Arrays.toString(count));
    
    if(allFiveOne(count, 10) && (spades == 5 || hearts == 5 || clubs == 5 || diamonds == 5)) return "royal flush";
		
		for(int i = 1; i <= 9; i++)
			if(allFiveOne(count, i) && (spades == 5 || hearts == 5 || clubs == 5 || diamonds == 5)) return "straight flush";
		
		for(int i = 1; i <= 13; i++) if(count[i] == 4) return "four of a kind";
		
		for(int i = 1; i <= 13; i++) for(int j = 1; j <= 13; j++) if(count[i] == 3 && count[j] == 2) return "full house";
		
		if(spades == 5 || hearts == 5 || clubs == 5 || diamonds == 5) return "flush";
		
		for(int i = 1; i <= 9; i++) if(allFiveOne(count, i)) return "straight";
		
		for(int i = 1; i <= 13; i++) if(count[i] == 3) return "three of a kind";
		
		for(int i = 1; i <= 13; i++) for(int j = 1; j <= 13; j++) if(i != j && count[i] == 2 && count[j] == 2) return "two pairs";
		
		for(int i = 1; i <= 13; i++) if(count[i] == 2) return "pair";
		
		return "high card";
	}
	
	private static boolean allFiveOne(int[] count, int index){
		for(int i = index; i < index + 5; i++) if(count[i] != 1) return false;
		return true;
	}
}
    

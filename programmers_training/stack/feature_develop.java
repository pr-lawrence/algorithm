import java.util.Arrays;

class Solution {
  public static int[] solution7(int[] progresses, int[] speeds) {
		int[] answer = new int[progresses.length];
		int[] result = new int[progresses.length];

		for(int i = 0 ; i < progresses.length; i++) {
			result[i] = (int)Math.ceil((double)(100 - progresses[i]) / (double)speeds[i]);
		}

		int index = 0;
		for(int j = 0, mindate = result[0], count = 0 ; j < result.length; j++) {
			count++;

			if(j + 1 >= result.length) {
				answer[index++] = count;
				break;
			}

			if(mindate < result[j+1]) {
				answer[index++] = count;
				mindate = result[j+1];
				count = 0;
			}
		}

		for(int k = 0 ; k < answer.length ; k++ ) {
			if (answer[k] == 0) {
				return Arrays.copyOf(answer, k);
			}
		}

		return answer;
	}
}

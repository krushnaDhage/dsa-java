// class Solution {
//     public boolean checkString(String s) {
//         int n=s.length();
//         if(n==0)
//         {
//             return true;
//         }
//         for(int i=0;i<n;i++)
//         {
//             if(s.charAt(i)=='b'&&i!=n)
//             {
//                 for(int j=i+1;j<n;j++)
//                 {
//                     if(s.charAt(j)=='a')
//                     {
//                         return false;
//                     }
//                 }
//             }
//         }
//         return true;
//     }
// }

class Solution {
    public boolean checkString(String s) {
        boolean foundB = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                foundB = true;
            }

            if (s.charAt(i) == 'a' && foundB) {
                return false;
            }
        }

        return true;
    }
}

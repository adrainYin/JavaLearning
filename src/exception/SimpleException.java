package exception;

class SimpleException extends Exception{
    public static class InheritingException{
        public void fun() throws Exception{
            System.out.println("从InheritingException抛出异常");
            throw new SimpleException();
        }
    }

    public static void main(String[] args) {
        InheritingException inheritingException = new InheritingException();
        try {
            inheritingException.fun();
        } catch (Exception e) {
            System.out.println("成功捕获异常");
        }
    }
}

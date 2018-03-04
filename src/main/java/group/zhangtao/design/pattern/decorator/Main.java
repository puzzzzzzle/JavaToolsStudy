package group.zhangtao.design.pattern.decorator;

public class Main {
    public static void main(String[] args) {
        Player myPlayer = new MyPlayer();
        Player anotherPlayer = new AnotherPlayer();
        Player qSkillPlayer = new QDecoratorSkills(myPlayer);
        Player wSkillPlayer = new WDecoratorSkills(anotherPlayer);
        System.out.println("正常模式：");
        myPlayer.useSkills();
        anotherPlayer.useSkills();
        System.out.println("装饰模式：");
        qSkillPlayer.useSkills();
        wSkillPlayer.useSkills();
        System.out.println("装饰多次：");
        Player doublePlayer = new QDecoratorSkills(myPlayer);
        Player doublePlayer1 = new WDecoratorSkills(doublePlayer);
        doublePlayer1.useSkills();
    }
}

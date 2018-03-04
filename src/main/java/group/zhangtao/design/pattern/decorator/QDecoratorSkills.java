package group.zhangtao.design.pattern.decorator;

public class QDecoratorSkills extends DecoratorSkills {
    public QDecoratorSkills(Player player) {
        super(player);
    }

    @Override
    public void useSkills() {
        super.useSkills();
        useThisSkill();
    }

    private void useThisSkill(){
        System.out.println("使用了Q技能!");
    }
}

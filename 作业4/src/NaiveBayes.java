import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;//filter build
import weka.filters.unsupervised.attribute.Normalize;//老三样
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Standardize;
public class NaiveBayes {
    //填充数据集 使用datasource
    public static Instances fill(String location) throws Exception {
        return DataSource.read(location);
    }
    //创建过滤器并预处理 使用filter
    public static Instances preload(Instances data,boolean if_replace_missing_values,boolean if_standardize,boolean if_normalize) throws Exception {
        //缺失值处理
        if(if_replace_missing_values){
            Filter ReplaceMissingValues_filter = new ReplaceMissingValues();
            ReplaceMissingValues_filter.setInputFormat(data);
            data = Filter.useFilter(data,ReplaceMissingValues_filter);
        }
        //标准化
        if(if_standardize){
            Filter Standardize_filter = new Standardize();
            Standardize_filter.setInputFormat(data);
            data = Filter.useFilter(data,Standardize_filter);
        }
        //规范化
        if(if_normalize){
            Filter Normalize_filter = new Normalize();
            Normalize_filter.setInputFormat(data);
            data = Filter.useFilter(data,Normalize_filter);
        }
        return data;
    }
    //数据降维
    public static void classification(Instances data) {
        Instances ins ;
        Classifier cfs  ;
        try{
            ins = data;
            ins.setClassIndex(ins.numAttributes()-1);//样本

            cfs = (Classifier) Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();//创建分类器但并未填充

            cfs.buildClassifier(ins);//填充

            //test1 用本身进行测试
            Instance testInst;
            Evaluation testingEvaluation = new Evaluation(ins);
            int length = ins.numInstances();
            for(int i=0;i<length;i++){
                testInst = ins.instance(i);
                testingEvaluation.evaluateModelOnceAndRecordPrediction(cfs,testInst);//解释 testingEvaluation是一个评估集，cfs是评估方案，testInst是被评估项
            }
            System.out.println(testingEvaluation.toSummaryString());
            System.out.println("All original testing success rate :" + (1-testingEvaluation.errorRate()));

            //test2 用给的进行测试
            Instances testData = fill("spambase.test.arff");
            length = testData.numInstances();
            testingEvaluation=new Evaluation(ins);
            for(int i=0 ;i<length;i++){
                testInst = ins.instance(i);
                testingEvaluation.evaluateModelOnceAndRecordPrediction(cfs,testInst);
                System.out.println(testingEvaluation.toSummaryString());
            }
            System.out.println("Test success rate: " + (1-testingEvaluation.errorRate()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        //1.填充arff数据集
        Instances data = fill("spambase.train.arff");

        //2.按照需求预处理（在本代码中预处理前后不变）
        data = preload(data,true,true,true);

        //3.进行测试
        classification(data);
    }
}

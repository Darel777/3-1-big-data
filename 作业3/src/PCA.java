//按代码顺序import
import weka.attributeSelection.*;//用于不同方法
import weka.core.Instances;//实例
import weka.core.converters.ConverterUtils.DataSource;//数据源提取实例
import weka.filters.Filter;//过滤器标定
import weka.filters.unsupervised.attribute.ReplaceMissingValues;//缺失值处理
import weka.filters.unsupervised.attribute.Standardize;//标准化
import weka.filters.unsupervised.attribute.Normalize;//规范化

public class PCA {
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
    public static Instances reduce(Instances data) throws Exception {
        PrincipalComponents pca = new PrincipalComponents();
        pca.buildEvaluator(data);
        pca.setMaximumAttributeNames(3);
        System.out.println(pca);//结果1
        Ranker ranker =new Ranker();
        AttributeSelection selection = new AttributeSelection();
        selection.setSearch(ranker);
        selection.setEvaluator(pca);
        selection.SelectAttributes(data);
        data = selection.reduceDimensionality(data);//Attribute Selection 调用PCA
        return data;
    }
    public static void main(String[] args) throws Exception {
        //1.填充arff数据集
        Instances data = fill("diabetes.arff");

        //2.按照需求预处理
        data = preload(data,true,true,true);

        //3.数据降维并保存结果
        data = reduce(data);
        System.out.println(data);//结果2


    }
}
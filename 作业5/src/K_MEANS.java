import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;//filter build
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Standardize;
public class K_MEANS {
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
    public static void cluster(Instances data) {
        Instances ins ;
        SimpleKMeans KM ;
        try{
            ins = data;
            KM = new SimpleKMeans();
            KM.setNumClusters(6);//分为6类
            KM.buildClusterer(ins);
            System.out.println(KM.preserveInstancesOrderTipText());
            System.out.println(KM);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        //1.填充arff数据集
        Instances data = fill("bank_data.arff");

        //2.按照需求预处理（在本代码中预处理前后不变）
        data = preload(data,true,true,true);

        //3.进行测试
        cluster(data);
    }
}

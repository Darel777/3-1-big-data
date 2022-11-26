import math
import csv


def read_movie(filename):
    with open(filename, encoding='utf-8') as f:
        reader = csv.reader(f)
        movies = [[row[0], row[1], row[2]] for row in reader]
    return movies


def read_userRating(filename):
    # userId, movieId, rating, timestamp
    with open(filename, encoding='utf-8') as f:
        reader = csv.reader(f)
        ratings = [[row[0], row[1], row[2]] for row in reader]
    return ratings


def ratingsArray(movies, ratings):  # 生成评分矩阵
    user_rating_array = []
    number = 0
    user_rating_list = []
    for userRating in ratings:  # 建立用户的评分列表集合
        if str(number) == userRating[0]:
            user_rating_list.append([userRating[1], userRating[2]])
            # print(number,userRating[0],userRating[1], userRating[2])
        else:
            user_rating_array.append([number, user_rating_list])
            # print(number, user_rating_list)
            user_rating_list = []
            number = number + 1
            user_rating_list.append([userRating[1], userRating[2]])
    user_rating_array.append([number, user_rating_list])  # 添加最后一个
    # print(user_rating_array)
    return user_rating_array


def CosSimilarity(UserId, user_rating_list, movies):
    # test_rating_list = user_rating_list[UserId][1]
    testAllMovieRating = GetAllMovieRating(user_rating_list[UserId][1], movies)
    print(UserId, testAllMovieRating)
    # print(test_rating_list)
    # print(len(user_rating_list))  # 611个，包含下标0
    resCos = []
    for id in range(len(user_rating_list)):
        if id == UserId or id == 0:
            continue
        userAllMovieRating = GetAllMovieRating(user_rating_list[id][1], movies)
        # print(id, userAllMovieRating)
        # 计算余弦相似度
        Cos = CosFunction(testAllMovieRating, userAllMovieRating)
        resCos.append([id, Cos])
    print(resCos)
    # key=(lambda x:x[1]),reverse=True
    res1 = sorted(resCos, key=(lambda x: x[1]), reverse=True)  # 取前20个与目标用户相似的用户
    res1 = res1[:20]
    print(res1)

    # 求前20个用户的所有电影评分矩阵
    res1AllMovieRating = []
    for item in res1:
        userAllMovieRating = GetAllMovieRating(user_rating_list[item[0]][1], movies)
        print(item[0], userAllMovieRating)
        res1AllMovieRating.append([item[0], userAllMovieRating])

    # 前20个用户的Cos余弦相似度求和
    sum2 = 0
    for i in range(len(res1)):
        sum2 = sum2 + math.sqrt(res1[i][1])
    # sum2 = math.sqrt(sum2)
    print(sum2)

    # 求所有电影对目标用户的推荐评分，目标用户看过的电影推荐评分设置为0
    MovieRecommend = []
    for i in range(len(testAllMovieRating)):
        recommend = 0
        sum1 = 0
        for j in range(len(res1)):
            sum1 = sum1 + res1[j][1] * float(res1AllMovieRating[j][1][i])
        recommend = sum1 / sum2
        if testAllMovieRating[i] != 0:
            recommend = 0
        MovieRecommend.append([i, recommend])
    print(MovieRecommend)
    MovieTop = sorted(MovieRecommend, key=(lambda x: x[1]), reverse=True)  # 根据推荐评分对电影排序
    print(MovieTop)
    Recommend = MovieTop[:20]
    print(Recommend)

    return Recommend


def CosFunction(test, user):  # 计算两用户之间余弦相似度
    sum1 = 0
    sum2 = 0
    sum3 = 0
    for i in range(len(test)):
        sum1 = sum1 + test[i] * user[i]
        sum2 = sum2 + math.pow(test[i], 2)
        sum3 = sum3 + math.pow(user[i], 2)
    CosTotal = sum1 / (math.sqrt(sum2) * math.sqrt(sum3))
    # print(CosTotal)
    return CosTotal


def GetAllMovieRating(user_rating, movies):  # 单个用户对九千多部电影的评分矩阵
    userAllMovieRating = []
    # print(user_rating)
    for movie in movies:
        movieId = movie[0]
        userRating = 0
        if movieId != "movieId":
            for userMovieId, userMovieRating in user_rating:
                if movieId == userMovieId:
                    userRating = float(userMovieRating)
                    break
        userAllMovieRating.append(userRating)
    userAllMovieRating[0] = 0  # 把下标为0项置为0 下标为0不代表是电影
    return userAllMovieRating


def RecommendMovies(movies, recommend):
    for item, item2 in recommend:
        print(movies[item][1], '\t', movies[item][2])


if __name__ == '__main__':
    movies = read_movie('movies.csv')
    ratings = read_userRating('ratings.csv')
    user_rating_list = ratingsArray(movies, ratings)
    recommend = CosSimilarity(10, user_rating_list, movies)
    RecommendMovies(movies, recommend)

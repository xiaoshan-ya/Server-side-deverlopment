#include <stdio.h>
//#include <stdlib.h>
//#include <errno.h>
#include <string.h>
//#include <netdb.h>
#include <sys/types.h>
//#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int sockfd, numbytes;
    char buf[BUFSIZ];
    char serverIP[20];
    struct sockaddr_in their_addr;
    printf("break!");
    while ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
        ;
    printf("please input server ip:\n");
    scanf("%s",serverIP);
    their_addr.sin_family = AF_INET;
    their_addr.sin_port = htons(8000);
    their_addr.sin_addr.s_addr = inet_addr(serverIP);
    bzero(&(their_addr.sin_zero), 8);

    while (connect(sockfd, (struct sockaddr *)&their_addr, sizeof(struct sockaddr)) == -1)
        ;
    printf("Get the Server~Cheers!\n");
    numbytes = recv(sockfd, buf, BUFSIZ, 0); //接收服务器端信息
    buf[numbytes] = '\0';
    printf("%s", buf);
    while (1)
    {
        printf("Enter some thing:");
        scanf("%s", buf);
        numbytes = send(sockfd, buf, strlen(buf), 0);
        numbytes = recv(sockfd, buf, BUFSIZ, 0);
        buf[numbytes] = '\0';
        printf("received:%s\n", buf);
    }
    close(sockfd);
    return 0;
}

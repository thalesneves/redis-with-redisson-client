# Use a imagem oficial do Redis
FROM redis:latest

# Copie o arquivo de configuração personalizado para dentro do container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Informe ao Redis para usar o arquivo de configuração personalizado
CMD ["redis-server", "/usr/local/etc/redis/redis.conf"]
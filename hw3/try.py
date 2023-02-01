def get_ice_cream_order_price(ice_cream_size, ice_cream_prices, ice_cream_sizes):
    for idx in range(len(ice_cream_sizes)):
        if ice_cream_size == ice_cream_sizes(idx):
            ice_cream_price = ice_cream_prices[idx]
    return ice_cream_price


def main():
    ice_cream_flavors = ['Vanilla', 'Chocolate', 'Strawberry']
    flavor_picked = get_ice_cream_flavor(ice_cream_flavors)
    print(flavor_picked)


if __name__ == '__main__':
    main()
